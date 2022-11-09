package com.example.data.models.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class CreateCardUnitDto(
    val id: Int,
    val fractionId: Int,
    val rankId: Int,
    val name: String,
    val isUnique: Boolean,
    val isNotMoreTwo: Boolean,
    val classIds: List<Int>,
    val flavor: String,
    val description: String,
    val imageUrl: String?
)
