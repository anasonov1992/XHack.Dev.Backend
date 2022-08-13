package com.example.routes

import com.example.controllers.ChatsController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getChats() {
    post("/api/getChats") {
        val controller = ChatsController(call)
        controller.getChats()
    }
}

fun Route.getChatMessages() {
    post("/api/getChatMessages") {
        val controller = ChatsController(call)
        controller.getChatMessages()
    }
}

fun Route.createP2PChat() {
    post("/api/createP2PChat") {
        val controller = ChatsController(call)
        controller.createP2PChat()
    }
}

fun Route.createTeamChat() {
    post("/api/createTeamChat") {
        val controller = ChatsController(call)
        controller.createTeamChat()
    }
}

fun Route.sendMessage() {
    post("/api/sendMessage") {
        val controller = ChatsController(call)
        controller.sendMessage()
    }
}

fun Route.deleteChat() {
    delete("/api/deleteChat") {
        val controller = ChatsController(call)
        controller.deleteChat()
    }
}