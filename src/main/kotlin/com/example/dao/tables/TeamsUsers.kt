package com.example.dao.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object TeamsUsers: IntIdTable() {
    var team = reference("team", Teams)
    var user = reference("user", Users)
}