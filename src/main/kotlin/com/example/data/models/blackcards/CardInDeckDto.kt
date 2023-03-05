package com.example.data.models.blackcards

import com.example.primitives.CardUniqueType
import kotlinx.serialization.Serializable

@Serializable
data class CardInDeckDto(
    val id: Int,
    val uniqueType: CardUniqueType,
    val amountInDeck: Int,
    val imageUrl: String?,
    val imageFullUrl: String?
)
