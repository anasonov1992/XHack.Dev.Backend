package com.example.data.models.blackcards

@kotlinx.serialization.Serializable
data class FractionDto(
    val id: Int,
    val name: String,
    val description: String,
    val artUrl: String? = null
)
