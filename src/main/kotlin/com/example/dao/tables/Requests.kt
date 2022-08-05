package com.example.dao.tables

import com.example.primitives.RequestType
import org.jetbrains.exposed.dao.id.IntIdTable

object Requests : IntIdTable() {
    val user = reference("user", Users).nullable()
    val team = reference("team", Teams).nullable()
    val type = enumeration<RequestType>("type")
    val isCanceled = bool("isCanceled")
}