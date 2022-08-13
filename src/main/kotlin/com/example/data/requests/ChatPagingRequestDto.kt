package com.example.data.requests

@kotlinx.serialization.Serializable
data class ChatPagingRequestDto(
    val chatId: Int,
    val pageSize: Int,
    val pageNumber: Long
)

