package com.example.data.models.blackcards

import com.example.data.primitives.blackcards.RankType
import kotlinx.serialization.Serializable

@Serializable
data class RankDto(
    val id: Int,
    val type: RankType,
    val displayName: String,
    val maxCardsOfRankCount: Int,
    val cardsUpText: String
)
