package com.example.dao.entities

import com.example.dao.tables.ChatMessages
import com.example.dao.tables.Chats
import com.example.dao.tables.ChatsUsers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Chat(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Chat>(Chats)

    var participants by User via ChatsUsers
    var team by Team optionalReferencedOn Chats.team
    var type by Chats.type
    val messages by ChatMessage referrersOn ChatMessages.chat
    var created by Chats.created
}