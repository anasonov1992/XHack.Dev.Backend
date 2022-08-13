package com.example.dao.tables

import org.jetbrains.exposed.sql.Table

object TeamsUsers: Table() {
    val team = reference("team", Teams)
    val user = reference("user", Users)
}