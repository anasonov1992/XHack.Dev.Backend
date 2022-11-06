package com.example.data.models.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class RankDto(
    val id: Int,
    val displayName: String
)
