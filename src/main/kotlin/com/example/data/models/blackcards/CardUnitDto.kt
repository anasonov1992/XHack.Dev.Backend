package com.example.data.models.blackcards

import com.example.primitives.CardUniqueType
import kotlinx.serialization.Serializable

@Serializable
data class CardUnitDto(
    val id: Int,
    val rank: RankDto,
    val name: String,
    val uniqueType: CardUniqueType,
    val classes: List<UnitClassDto>,
    val flavor: String,
    val description: String,
    val imageUrl: String?
)
