package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.Chat
import com.example.dao.entities.ChatMessage
import com.example.dao.entities.Team
import com.example.dao.entities.User
import com.example.dao.interfaces.ChatsDataSource
import com.example.dao.mappers.toChatDto
import com.example.dao.mappers.toChatMessageDto
import com.example.dao.tables.ChatMessages
import com.example.dao.tables.Chats
import com.example.dao.tables.ChatsUsers
import com.example.dao.tables.Users
import com.example.data.models.*
import com.example.data.requests.ChatPagingRequestDto
import com.example.data.requests.PagingRequestDto
import com.example.primitives.ChatType
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime
import java.util.*

class ChatsDataSourceImpl: ChatsDataSource {
    private val _utcTimeZone = TimeZone.getTimeZone("UTC").toZoneId()

    override suspend fun getChats(userId: Int, request: PagingRequestDto): List<ChatDto> = dbQuery {
        request.run {
            Chat.wrapRows(ChatsUsers.innerJoin(Chats)
                .select { ChatsUsers.user eq userId })
                .limit(pageSize, pageSize * pageNumber)
                .map { it.toChatDto(userId) }
        }
    }

    override suspend fun getChatMessages(request: ChatPagingRequestDto): List<ChatMessageDto> = dbQuery {
        request.run {
            ChatMessage.wrapRows(Chats.innerJoin(ChatMessages)
                .select { Chats.id eq chatId })
                .limit(pageSize, pageSize * pageNumber)
                .map { it.toChatMessageDto() }
        }
    }

    override suspend fun createP2PChat(userId: Int, data: CreateP2PChatDto): ChatDto? = dbQuery {
        //TODO check that P2P chat already exist
//        ChatsUsers.select { ChatsUsers.user eq userId }

        val initiator = User.findById(data.initiatorId)
        val companion = User.findById(data.companionId)
        if (initiator == null || companion == null)
            null

        Chat.new {
            participants = SizedCollection(listOf(initiator!!, companion!!))
            type = ChatType.P2P
            created = LocalDateTime.now(_utcTimeZone)
        }.toChatDto(userId)
    }

    override suspend fun createTeamChat(userId: Int, data: CreateTeamChatDto): ChatDto? = dbQuery {
        var dbTeam = Team.findById(data.teamId)
        if (dbTeam == null || !dbTeam.users.any())
            null

        Chat.new {
            team = dbTeam!!
            participants = dbTeam.users
            type = ChatType.Team
            created = LocalDateTime.now(_utcTimeZone)
        }.toChatDto(userId)
    }

    override suspend fun createMessage(data: SendMessageDto): ChatMessageDto? = dbQuery {
        val dbChat = Chat.findById(data.chatId)
        val dbSender = User.findById(data.senderId)
        if (dbChat == null || dbSender == null)
            null

        ChatMessage.new {
            chat = dbChat!!
            sender = dbSender!!
            guid = UUID.randomUUID()
            text = data.text
            created = LocalDateTime.now(_utcTimeZone)
        }.toChatMessageDto()
    }

    override suspend fun deleteChat(chatId: Int) = dbQuery {
        val chat = Chat.findById(chatId)
        if (chat == null)
            null

        ChatsUsers.deleteWhere { ChatsUsers.chat eq chatId }
        ChatMessages.deleteWhere { ChatMessages.chat eq chatId }
        chat?.delete()
    }
}