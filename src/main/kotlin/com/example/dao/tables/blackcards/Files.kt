package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable

object Files : IntIdTable() {
    val guid = uuid("guid")
    val name = varchar("name", 128)
    val extension = varchar("extension", 32)
    val path = varchar("path", 128)
    val fileBinary = binary("file_binary").nullable()
}