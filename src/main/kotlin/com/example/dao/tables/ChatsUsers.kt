package com.example.dao.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object ChatsUsers: IntIdTable() {
    val chat = reference("chat", Chats)
    val user = reference("user", Users)
}