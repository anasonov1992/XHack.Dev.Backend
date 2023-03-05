package com.example.data.models.blackcards

import com.example.primitives.CardUniqueType
import kotlinx.serialization.Serializable

@Serializable
data class CreateCardUnitDto(
    val id: Int,
    val fractionId: Int,
    val rankId: Int,
    val name: String,
    val uniqueType: CardUniqueType,
    val classIds: List<Int>,
    val flavor: String,
    val description: String,
    val imageUrl: String?
)