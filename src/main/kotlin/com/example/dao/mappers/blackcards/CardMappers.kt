package com.example.dao.mappers.blackcards

import com.example.dao.entities.blackcards.Card
import com.example.data.models.blackcards.CardArtDto

fun Card.toCardArtDto(): CardArtDto {
    return CardArtDto(id.value, name, description, artUrl, fraction.name)
}