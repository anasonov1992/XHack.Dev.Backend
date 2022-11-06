package com.example.data.requests.blackcards

import kotlinx.serialization.Serializable

@Serializable
data class CardsPagingRequestDto(
    val fractionId: Int,
    val pageSize: Int = Int.MAX_VALUE,
    val pageNumber: Long
)
