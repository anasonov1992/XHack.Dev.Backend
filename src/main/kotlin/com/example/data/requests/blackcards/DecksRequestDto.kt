package com.example.data.requests.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class DecksRequestDto(
    val deckId: Int
)
