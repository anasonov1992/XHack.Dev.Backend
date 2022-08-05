package com.example.dao.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Users: IntIdTable() {
    val firstName = varchar("first_name", 128)
    val lastName = varchar("last_name", 128)
    val email = varchar("email", 128).uniqueIndex()
    val password = varchar("password", 128)
    val salt = varchar("salt", 128)
}