package com.example.data.models.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class DeckRankGroupDto(
    val rank: RankDto,
    val cards: List<CardInDeckDto>
)
