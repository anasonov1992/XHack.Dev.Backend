package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable

object Ranks: IntIdTable() {
    val displayName = varchar("display_name", 32)
}