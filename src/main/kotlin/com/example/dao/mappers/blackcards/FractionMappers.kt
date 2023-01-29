package com.example.dao.mappers.blackcards

import com.example.dao.entities.blackcards.Fraction
import com.example.data.models.blackcards.FractionDto

fun Fraction.toFractionDto(): FractionDto {
    return FractionDto(id.value, type, name, description, artUrl, logoUrl)
}