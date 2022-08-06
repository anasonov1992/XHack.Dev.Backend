package com.example.dao.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Tags : IntIdTable() {
    val name = varchar("name", 128)
}