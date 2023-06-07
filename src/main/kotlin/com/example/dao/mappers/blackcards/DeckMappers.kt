package com.example.dao.mappers.blackcards

import com.example.dao.entities.blackcards.CardInDeck
import com.example.dao.entities.blackcards.CardUnit
import com.example.dao.entities.blackcards.Deck
import com.example.data.models.blackcards.CardInDeckDto
import com.example.data.models.blackcards.DeckDto
import com.example.data.models.blackcards.DeckShortDto

fun CardInDeck.toCardInDeckDto() = CardInDeckDto(id.value, card.uniqueType, amountInDeck, card.imageUrl, card.imageFullUrl)

fun CardUnit.toCardInDeckDto(amountInDeck: Int = 0) = CardInDeckDto(id.value, uniqueType, amountInDeck, imageUrl, imageFullUrl)

fun Deck.toDeckDto() = DeckDto(id.value, name, fraction.toFractionDto())

fun Deck.toDeckShortDto() = DeckShortDto(id.value, name, fraction.id.value)