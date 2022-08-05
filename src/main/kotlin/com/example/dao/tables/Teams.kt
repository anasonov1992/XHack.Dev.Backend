package com.example.dao.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Teams : IntIdTable() {
    val name = varchar("name", 128)
    val description = text("description")
    val maxUsersCount = integer("max_users_count")
    val avatarUrl = varchar("avatar_url", 512).nullable()
}