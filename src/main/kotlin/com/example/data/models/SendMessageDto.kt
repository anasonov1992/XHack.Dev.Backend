package com.example.data.models

@kotlinx.serialization.Serializable
data class SendMessageDto(
    val text: String,
    val senderId: Int,
    val chatId: Int
)
