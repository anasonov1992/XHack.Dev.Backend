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
}