package com.example.dao.mappers.blackcards

import com.example.dao.entities.blackcards.Fraction
import com.example.data.models.blackcards.FractionDto
import com.example.data.models.blackcards.FractionShortDto

fun Fraction.toFractionDto(): FractionDto {
    return FractionDto(id.value, type, name, description, artUrl, logoUrl)
}

fun Fraction.toFractionShortDto(): FractionShortDto {
    return FractionShortDto(id.value, type, name, logoUrl)
}
