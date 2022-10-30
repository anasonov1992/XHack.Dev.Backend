package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable

object Fractions : IntIdTable() {
    val name = varchar("name", 128)
    val description = text("description")
    val artUrl = varchar("art_url", 512).nullable()
}