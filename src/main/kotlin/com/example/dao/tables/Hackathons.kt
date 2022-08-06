package com.example.dao.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Hackathons: IntIdTable()  {
    val name = varchar("name", 128)
    val description = text("description")
    val isOnline = bool("isOnline")
    val location = varchar("location", 128)
    val siteUrl = varchar("siteUrl", 128)
    val avatarUrl = varchar("avatarUrl", 128)
    val isBookmarked = bool("isBookmarked")
    //TODO многие ко многим для таблицы Tags
    //    val tags: List<TagDto>,
}