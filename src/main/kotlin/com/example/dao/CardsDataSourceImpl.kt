package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.blackcards.Card
import com.example.dao.interfaces.blackcards.CardsDataSource
import com.example.dao.mappers.blackcards.toCardArtDto
import com.example.dao.tables.blackcards.Cards
import com.example.dao.tables.blackcards.Fractions
import com.example.data.models.blackcards.CardArtDto
import com.example.data.requests.PagingRequestDto
import org.jetbrains.exposed.sql.selectAll

class CardsDataSourceImpl : CardsDataSource {
    override suspend fun getCardArts(request: PagingRequestDto): List<CardArtDto> = dbQuery {
        request.run {
            Card.wrapRows(Fractions.innerJoin(Cards).selectAll())
                .limit(pageSize, pageSize * pageNumber)
                .map { it.toCardArtDto() }
        }
    }

    override suspend fun createCardArt(cardArt: CardArtDto): CardArtDto = dbQuery {
        Card.new {
            name = cardArt.name
            description = cardArt.description
            artUrl = cardArt.artUrl
        }.toCardArtDto()
    }
}