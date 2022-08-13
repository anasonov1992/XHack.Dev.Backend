package com.example.dao.interfaces

import com.example.data.models.*
import com.example.data.requests.ChatPagingRequestDto
import com.example.data.requests.PagingRequestDto

interface ChatsDataSource {
    suspend fun getChats(userId: Int, request: PagingRequestDto): List<ChatDto>
    suspend fun getChatMessages(request: ChatPagingRequestDto): List<ChatMessageDto>
    suspend fun createP2PChat(userId: Int, data: CreateP2PChatDto): ChatDto?
    suspend fun createTeamChat(userId: Int, data: CreateTeamChatDto): ChatDto?
    suspend fun createMessage(data: SendMessageDto): ChatMessageDto?
    suspend fun deleteChat(chatId: Int): Unit?
}