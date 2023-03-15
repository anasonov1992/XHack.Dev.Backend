package com.example.data.models.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class DeckDto(
    val id: Int,
    val name: String,
    val fraction: FractionDto
)
