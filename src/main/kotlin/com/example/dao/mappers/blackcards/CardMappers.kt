package com.example.dao.mappers.blackcards

import com.example.dao.entities.blackcards.Card
import com.example.data.models.blackcards.CardArtDetailDto
import com.example.data.models.blackcards.CardArtDto

fun Card.toCardArtDto(): CardArtDto {
    return CardArtDto(id.value, name, artUrl, fraction.name)
}

fun Card.toCardArtDetailDto(): CardArtDetailDto {
    return CardArtDetailDto(id.value, name, description, artUrl, fraction.name)
}