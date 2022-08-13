package com.example.data.models

import com.example.utils.UUIDSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ChatMessageDto(
    @Serializable(with = UUIDSerializer::class)
    val guid: UUID,
    val text: String,
    @Contextual
    val created: java.time.LocalDateTime,
    val sender: UserDto
)
