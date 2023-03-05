package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Decks: IntIdTable() {
    val name = varchar("name", 256)
    val created = datetime("created")
    val fraction = reference("fraction", Fractions)
}