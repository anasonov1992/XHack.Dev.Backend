package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.blackcards.CardInDeck
import com.example.dao.entities.blackcards.CardUnit
import com.example.dao.entities.blackcards.Deck
import com.example.dao.entities.blackcards.Fraction
import com.example.dao.interfaces.blackcards.DeckDataSource
import com.example.dao.mappers.blackcards.toCardInDeckDto
import com.example.dao.mappers.blackcards.toDeckDto
import com.example.dao.mappers.blackcards.toRankDto
import com.example.dao.tables.blackcards.CardUnits
import com.example.dao.tables.blackcards.CardsInDeck
import com.example.dao.tables.blackcards.Decks
import com.example.dao.tables.blackcards.Fractions
import com.example.data.models.blackcards.CreateDeckDto
import com.example.data.models.blackcards.DeckDto
import com.example.data.models.blackcards.DeckRankGroupDto
import com.example.utils.DbResult
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime
import java.util.*

class DeckDataSourceImpl: DeckDataSource {
    private val _utcTimeZone = TimeZone.getTimeZone("UTC").toZoneId()

    override suspend fun getFractionCards(fractionId: Int): List<DeckRankGroupDto> = dbQuery {
        CardUnit.wrapRows(Fractions.innerJoin(CardUnits)
            .select { Fractions.id eq fractionId })
            .groupBy { it.rank }
            .map { rankGroup -> DeckRankGroupDto(rankGroup.key.toRankDto(), rankGroup.value.map { it.toCardInDeckDto() }) }
    }

    override suspend fun getDeckCards(deckId: Int): List<DeckRankGroupDto> = dbQuery {
        CardInDeck.wrapRows(Decks.innerJoin(CardsInDeck)
            .select { Decks.id eq deckId })
            .groupBy({ it.card.rank }, { Pair(it.card, it.amountInDeck) })
            .map { rankGroup -> DeckRankGroupDto(rankGroup.key.toRankDto(), rankGroup.value.map { it.first.toCardInDeckDto(it.second) }) }
    }

    override suspend fun createDeck(createdDeck: CreateDeckDto): DbResult<DeckDto> = dbQuery {
        val dbFraction = Fraction.findById(createdDeck.fractionId) ?: return@dbQuery DbResult.NotFound

        val dbDeck = Deck.new {
            fraction = dbFraction
            name = createdDeck.deckName
            created = LocalDateTime.now(_utcTimeZone)
        }

        CardsInDeck.batchInsert(createdDeck.cardIds, shouldReturnGeneratedValues = false) { item ->
            this[CardsInDeck.card] = item.id
            this[CardsInDeck.amountInDeck] = item.amountInDeck
            this[CardsInDeck.deck] = dbDeck.id
        }

        DbResult.Success(
            dbDeck.toDeckDto()
        )
    }
}