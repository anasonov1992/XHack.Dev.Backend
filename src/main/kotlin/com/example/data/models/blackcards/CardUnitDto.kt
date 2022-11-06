package com.example.data.models.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class CardUnitDto(
    val id: Int,
    val rank: RankDto,
    val name: String,
    val isUnique: Boolean,
    val isNotMoreTwo: Boolean,
    val classes: List<UnitClassDto>,
    val flavor: String,
    val description: String,
    val imageUrl: String?
)
