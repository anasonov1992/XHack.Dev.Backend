package com.example.dao.interfaces

import com.example.data.models.*
import com.example.data.requests.ChatPagingRequestDto
import com.example.data.requests.PagingRequestDto
import com.example.utils.DbResult

interface ChatsDataSource {
    suspend fun getChats(userId: Int, request: PagingRequestDto): List<ChatDto>
    suspend fun getChatMessages(request: ChatPagingRequestDto): List<ChatMessageDto>
    suspend fun createP2PChat(userId: Int, data: CreateP2PChatDto): DbResult<ChatDto>
    suspend fun createTeamChat(userId: Int, data: CreateTeamChatDto): DbResult<ChatDto>
    suspend fun createMessage(data: SendMessageDto): DbResult<ChatMessageDto>
    suspend fun deleteChat(chatId: Int): Unit?
}