package com.example.data.models.blackcards

import com.example.data.primitives.blackcards.SpellType
import kotlinx.serialization.Serializable

@Serializable
data class SpellTypeDto(
    val id: Int,
    val type: SpellType,
    val displayName: String
)