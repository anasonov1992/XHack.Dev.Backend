package com.example.controllers

import com.example.dao.interfaces.ChatsDataSource
import com.example.data.models.ChatIdDto
import com.example.data.models.CreateP2PChatDto
import com.example.data.models.CreateTeamChatDto
import com.example.data.models.SendMessageDto
import com.example.data.requests.ChatPagingRequestDto
import com.example.data.requests.PagingRequestDto
import com.example.utils.Constants
import com.example.utils.DbResult
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class ChatsController(private val call: ApplicationCall) {
    private val chatsDataSource by KoinJavaComponent.inject<ChatsDataSource>(ChatsDataSource::class.java)

    suspend fun getChats() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val request = call.receiveOrNull<PagingRequestDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        call.respond(HttpStatusCode.OK, chatsDataSource.getChats(userId.toInt(), request))
    }

    suspend fun getChatMessages() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val request = call.receiveOrNull<ChatPagingRequestDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        call.respond(HttpStatusCode.OK, chatsDataSource.getChatMessages(request))
    }

    suspend fun createP2PChat() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val chatData = call.receiveOrNull<CreateP2PChatDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        when (val dbResult = chatsDataSource.createP2PChat(userId.toInt(), chatData)) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, "Sender or companion are not found")
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict, Constants.P2P_CHAT_EXISTS)
            is DbResult.Success -> call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }

    suspend fun createTeamChat() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val chatData = call.receiveOrNull<CreateTeamChatDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        when (val dbResult = chatsDataSource.createTeamChat(userId.toInt(), chatData)) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, Constants.TEAM_NOT_FOUND)
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict, Constants.TEAM_CHAT_EXISTS)
            is DbResult.Success -> call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }

    suspend fun sendMessage() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val messageData = call.receiveOrNull<SendMessageDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        when (val dbResult = chatsDataSource.createMessage(messageData)) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, Constants.CHAT_OR_SENDER_NOT_FOUND)
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict)
            is DbResult.Success -> call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }

    suspend fun deleteChat() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val chatData = call.receiveOrNull<ChatIdDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        val deletedChat = chatsDataSource.deleteChat(chatData.chatId)
        if (deletedChat == null) {
            call.respond(HttpStatusCode.BadRequest, Constants.CHAT_NOT_FOUND)
        }

        call.respond(HttpStatusCode.OK)
    }
}