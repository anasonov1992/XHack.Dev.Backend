package com.example.data.models.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class DeckGroupDto(
    val fraction: FractionShortDto,
    val decks: List<DeckShortDto>
)
