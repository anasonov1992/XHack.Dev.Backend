package com.example.controllers

import com.example.dao.interfaces.ChatsDataSource
import com.example.data.models.ChatIdDto
import com.example.data.models.CreateP2PChatDto
import com.example.data.models.CreateTeamChatDto
import com.example.data.models.SendMessageDto
import com.example.data.requests.ChatPagingRequestDto
import com.example.data.requests.PagingRequestDto
import com.example.utils.Constants
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

        val createdChat = chatsDataSource.createP2PChat(userId.toInt(), chatData)
        if (createdChat == null) {
            call.respond(HttpStatusCode.BadRequest, "Sender or companion are not found")
            return
        }

        call.respond(HttpStatusCode.OK, createdChat)
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

        val createdChat = chatsDataSource.createTeamChat(userId.toInt(), chatData)
        if (createdChat == null) {
            call.respond(HttpStatusCode.BadRequest, Constants.TEAM_NOT_FOUND)
            return
        }

        call.respond(HttpStatusCode.OK, createdChat)
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

        val sentMessage = chatsDataSource.createMessage(messageData)
        if (sentMessage == null) {
            call.respond(HttpStatusCode.BadRequest, "Chat or sender are not found")
            return
        }

        call.respond(HttpStatusCode.OK, sentMessage)
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