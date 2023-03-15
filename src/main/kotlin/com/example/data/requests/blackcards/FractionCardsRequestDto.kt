package com.example.data.requests.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class FractionCardsRequestDto(
    val fractionId: Int
)
