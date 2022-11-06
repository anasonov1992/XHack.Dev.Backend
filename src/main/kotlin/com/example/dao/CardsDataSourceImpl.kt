package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.blackcards.*
import com.example.dao.interfaces.blackcards.CardsDataSource
import com.example.dao.mappers.blackcards.*
import com.example.dao.tables.blackcards.CardUnits
import com.example.dao.tables.blackcards.Cards
import com.example.dao.tables.blackcards.Fractions
import com.example.dao.tables.blackcards.UnitClasses
import com.example.data.models.blackcards.*
import com.example.data.requests.SearchPagingRequestDto
import com.example.data.requests.blackcards.CardsPagingRequestDto
import com.example.utils.DbResult
import org.jetbrains.exposed.sql.*

class CardsDataSourceImpl : CardsDataSource {
    override suspend fun getCardArts(request: SearchPagingRequestDto): List<CardArtDto> = dbQuery {
        val query = Fractions.innerJoin(Cards).selectAll() //

        request.filter?.let {
            query.andWhere { (Cards.name.lowerCase() like "%${request.filter}%") or (Fractions.name.lowerCase() like "%${request.filter}%") }
        }

        request.run {
            Card.wrapRows(query)
                .limit(pageSize, pageSize * pageNumber)
                .map { it.toCardArtDto() }
        }
    }

    override suspend fun getCardArtDetail(id: Int): DbResult<CardArtDetailDto> = dbQuery {
        val dbCardDetail = Card.findById(id) ?: return@dbQuery DbResult.NotFound
        DbResult.Success(dbCardDetail.toCardArtDetailDto())
    }

    override suspend fun createCardArt(cardArt: CreateCardArtDto): DbResult<CardArtDto> = dbQuery {
        val dbFraction = Fraction.findById(cardArt.fractionId) ?: return@dbQuery DbResult.NotFound

        DbResult.Success(
            Card.new {
                name = cardArt.name
                description = cardArt.description
                artUrlOriginal = cardArt.artUrlOriginal
                artUrlDetail = cardArt.artUrlDetail
                artUrlList = cardArt.artUrlList
                fraction = dbFraction
            }.toCardArtDto()
        )
    }

    override suspend fun updateCardArt(cardArt: CreateCardArtDto): DbResult<CardArtDetailDto> = dbQuery {
        val dbCardArt = Card.findById(cardArt.id) ?: return@dbQuery DbResult.NotFound

        DbResult.Success(
            dbCardArt.apply {
                name = cardArt.name
                description = cardArt.description
                artUrlOriginal = cardArt.artUrlOriginal
                artUrlDetail = cardArt.artUrlDetail
                artUrlList = cardArt.artUrlList
            }.toCardArtDetailDto()
        )
    }

    override suspend fun getRanks(): List<RankDto> = dbQuery {
        Rank.all().map { it.toRankDto() }
    }

    override suspend fun createRank(rank: RankDto): DbResult<RankDto> = dbQuery {
        DbResult.Success(
            Rank.new {
                displayName = rank.displayName
            }.toRankDto()
        )
    }

    override suspend fun getUnitClasses(): List<UnitClassDto> = dbQuery {
        UnitClass.all().map { it.toUnitClassDto() }
    }

    override suspend fun createUnitClass(unitClass: UnitClassDto): DbResult<UnitClassDto> = dbQuery {
        DbResult.Success(
            UnitClass.new {
                type = unitClass.type
                displayName = unitClass.displayName
            }.toUnitClassDto()
        )
    }

    override suspend fun getCardUnits(request: CardsPagingRequestDto): List<CardUnitDto> = dbQuery {
        request.run {
            CardUnit.wrapRows(Fractions.innerJoin(CardUnits)
                .select { Fractions.id eq fractionId })
                .limit(pageSize, pageSize * pageNumber)
                .map { it.toCardUnitDto() }
        }
    }

    override suspend fun createCardUnit(card: CardUnitDto): DbResult<CardUnitDto> = dbQuery {
        val dbRank = Rank.findById(card.rank.id) ?: return@dbQuery DbResult.NotFound

        var dbUnitClasses = emptyList<UnitClass>()
        if (card.classes.isNotEmpty()) {
            dbUnitClasses = UnitClass.find { UnitClasses.id inList card.classes.map { it.id } }.toList()
        }

        DbResult.Success(
            CardUnit.new {
                rank = dbRank
                isUnique = card.isUnique
                isNotMoreTwo = card.isNotMoreTwo
                unitClasses = SizedCollection(dbUnitClasses)
                flavor = card.flavor
                description = card.description
                imageUrl = card.imageUrl
            }.toCardUnitDto()
        )
    }
}