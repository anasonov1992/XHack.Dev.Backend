package com.example.dao.mappers

import com.example.dao.entities.Chat
import com.example.dao.entities.ChatMessage
import com.example.dao.entities.fullname
import com.example.data.models.ChatDto
import com.example.data.models.ChatMessageDto
import com.example.primitives.ChatType
import com.example.utils.Constants

fun Chat.toChatDto(userId: Int): ChatDto {
    val chatTeam = team
    val companion = participants.find { it.id.value != userId }
    val chatName = when(type) {
        ChatType.P2P -> companion?.fullname ?: Constants.UNKNOWN_TEXT
        ChatType.Team -> chatTeam?.name ?: Constants.UNKNOWN_TEXT
        else -> Constants.UNKNOWN_TEXT
    }

    return ChatDto(
        id.value,
        chatName,
        participants.map { it.toUserDto() },
        chatTeam?.toTeamDto(),
        type,
        messages.lastOrNull()?.toChatMessageDto()
//        created //FIXME
    )
}

fun ChatMessage.toChatMessageDto(): ChatMessageDto = ChatMessageDto(guid, text, sender.toUserDto()) //FIXME created