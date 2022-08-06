package com.example.dao.tables

import com.example.primitives.RequestType
import org.jetbrains.exposed.dao.id.IntIdTable

object Requests : IntIdTable() {
    val invitedUserId = integer("invitedUserId")
    val fromUser = optReference("fromUser", Users)
    val fromTeam = optReference("fromTeam", Teams)
    val type = enumeration<RequestType>("type")
    val isCanceled = bool("isCanceled")
}