package com.example.data.models.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class CreateCardSpellDto(
    val id: Int,
    val fractionId: Int,
    val name: String,
    val typesIds: List<Int>,
    val flavor: String,
    val description: String,
    val imageUrl: String?
)
