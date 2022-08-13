package com.example.dao.tables

import org.jetbrains.exposed.sql.Table

object ChatsUsers: Table() {
    val chat = reference("chat", Chats)
    val user = reference("user", Users)
}