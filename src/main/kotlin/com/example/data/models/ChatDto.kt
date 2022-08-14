package com.example.data.models

import com.example.primitives.ChatType
import kotlinx.serialization.Contextual

@kotlinx.serialization.Serializable
data class ChatDto(
    val id: Int,
    val name: String,
    val participants: List<UserDto>,
    val team: TeamDto?,
    val type: ChatType,
    val lastMessage: ChatMessageDto?
//    @Contextual //FIXME
//    val created: java.time.LocalDateTime
)
