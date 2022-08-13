package com.example.dao.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object ChatMessages: IntIdTable() {
    val guid = uuid("guid")
    val text = text("text")
    val chat = reference("chat", Chats)
    val sender = reference("sender", Users)
    val created = datetime("created")
}
