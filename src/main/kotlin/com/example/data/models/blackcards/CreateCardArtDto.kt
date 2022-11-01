package com.example.data.models.blackcards

@kotlinx.serialization.Serializable
data class CreateCardArtDto(
    val id: Int,
    val name: String,
    val description: String,
    val artUrlOriginal: String? = null,
    val artUrlDetail: String? = null,
    val artUrlList: String? = null,
    val fractionId: Int
)