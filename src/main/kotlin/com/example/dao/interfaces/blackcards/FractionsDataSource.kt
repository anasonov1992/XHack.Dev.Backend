package com.example.dao.interfaces.blackcards

import com.example.data.models.blackcards.FractionDto

interface FractionsDataSource {
    suspend fun getFractions(): List<FractionDto>
    suspend fun createFraction(team: FractionDto): FractionDto
}