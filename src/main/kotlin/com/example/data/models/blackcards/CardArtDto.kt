package com.example.data.models.blackcards

@kotlinx.serialization.Serializable
data class CardArtDto(
    val id: Int,
    val name: String,
    val description: String,
    val artUrl: String? = null,
    val fraction: String
)