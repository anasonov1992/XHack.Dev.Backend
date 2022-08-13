package com.example.dao.tables

import com.example.primitives.ChatType
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Chats: IntIdTable() {
    val team = optReference("team", Teams)
    val type = enumeration<ChatType>("type")
    val created = datetime("created")
}