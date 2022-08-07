package com.example.dao.tables

import org.jetbrains.exposed.sql.Table

object HackathonsTags: Table() {
    val hackathon = reference("hackathon", Hackathons)
    val tag = reference("tag", Tags)
}