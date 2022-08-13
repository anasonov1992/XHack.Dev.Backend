package com.example.dao.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Hackathons: IntIdTable()  {
    val name = varchar("name", 128)
    val description = text("description")
    val isOnline = bool("isOnline")
    val location = varchar("location", 128)
    //FIXME implement datetime
//    val startDate = datetime("startDate")
//    val endDate = datetime("endDate")
    val siteUrl = varchar("siteUrl", 128)
    val avatarUrl = varchar("avatarUrl", 128)
    val isBookmarked = bool("isBookmarked")
}