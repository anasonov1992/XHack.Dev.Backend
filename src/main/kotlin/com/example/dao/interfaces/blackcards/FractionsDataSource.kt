package com.example.dao.interfaces.blackcards

import com.example.data.models.blackcards.FractionDto
import com.example.utils.DbResult

interface FractionsDataSource {
    suspend fun getFractions(): List<FractionDto>
    suspend fun createFraction(fraction: FractionDto): FractionDto
    suspend fun updateFraction(fraction: FractionDto): DbResult<FractionDto>
}