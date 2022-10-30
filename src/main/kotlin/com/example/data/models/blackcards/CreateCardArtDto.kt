package com.example.data.models.blackcards

@kotlinx.serialization.Serializable
data class CreateCardArtDto(
    val name: String,
    val description: String,
    val artUrl: String? = null,
    val fractionId: Int
)