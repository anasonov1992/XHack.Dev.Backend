package com.example.data.models.blackcards

import com.example.data.primitives.blackcards.UnitClassType
import kotlinx.serialization.Serializable

@Serializable
data class UnitClassDto(
    val id: Int,
    val type: UnitClassType,
    val displayName: String
)
