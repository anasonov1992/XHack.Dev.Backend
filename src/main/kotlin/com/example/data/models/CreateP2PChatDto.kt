package com.example.data.models

@kotlinx.serialization.Serializable
data class CreateP2PChatDto(
    val initiatorId: Int,
    val companionId: Int
)
