package com.example.data.requests.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class DeckCardsRequestDto(
    val deckId: Int
)
