package com.example.dao.entities

import com.example.dao.tables.Teams
import com.example.dao.tables.TeamsUsers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Team(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Team>(Teams)

    var name by Teams.name
    var description by Teams.description
    var avatarUrl by Teams.avatarUrl
    var maxUsersCount by Teams.maxUsersCount
    var users by User via TeamsUsers
}