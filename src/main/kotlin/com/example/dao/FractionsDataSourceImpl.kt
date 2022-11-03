package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.blackcards.Card
import com.example.dao.entities.blackcards.Fraction
import com.example.dao.interfaces.blackcards.FractionsDataSource
import com.example.dao.mappers.blackcards.toCardArtDetailDto
import com.example.dao.mappers.blackcards.toFractionDto
import com.example.data.models.blackcards.CardArtDetailDto
import com.example.data.models.blackcards.CreateCardArtDto
import com.example.data.models.blackcards.FractionDto
import com.example.utils.DbResult

class FractionsDataSourceImpl : FractionsDataSource {
    override suspend fun getFractions(): List<FractionDto> = dbQuery {
        Fraction.all().map { it.toFractionDto() }
    }

    override suspend fun createFraction(fraction: FractionDto): FractionDto = dbQuery {
        Fraction.new {
            name = fraction.name
            description = fraction.description
            artUrl = fraction.artUrl
        }.toFractionDto()
    }

    override suspend fun updateFraction(fraction: FractionDto): DbResult<FractionDto> = dbQuery {
        val dbFraction = Fraction.findById(fraction.id) ?: return@dbQuery DbResult.NotFound

        DbResult.Success(
            dbFraction.apply {
                name = fraction.name
                description = fraction.description
                artUrl = fraction.artUrl
            }.toFractionDto()
        )
    }
}