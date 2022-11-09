package com.example.data.models.blackcards

import com.example.data.primitives.blackcards.FractionType

@kotlinx.serialization.Serializable
data class FractionDto(
    val id: Int,
    val type: FractionType,
    val name: String,
    val description: String,
    val artUrl: String? = null
)
