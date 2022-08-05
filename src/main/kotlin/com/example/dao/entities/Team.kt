package com.example.dao.entities

import com.example.dao.tables.Teams
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Team(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Team>(Teams)

    var name by Teams.name
    var description by Teams.description
    var maxUsersCount by Teams.maxUsersCount
    var avatarUrl by Teams.avatarUrl
}