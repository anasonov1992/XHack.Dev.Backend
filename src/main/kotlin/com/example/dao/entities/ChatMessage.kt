package com.example.dao.entities

import com.example.dao.tables.ChatMessages
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ChatMessage(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChatMessage>(ChatMessages)

    var guid by ChatMessages.guid
    var text by ChatMessages.text
    var chat by Chat referencedOn ChatMessages.chat
    var sender by User referencedOn ChatMessages.sender
    var created by ChatMessages.created
}