package com.example.data.models.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class CardSpellDto(
    val id: Int,
    val name: String,
    val types: List<SpellTypeDto>,
    val flavor: String,
    val description: String,
    val imageUrl: String?
)
