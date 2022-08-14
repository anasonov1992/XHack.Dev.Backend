package com.example.dao.entities

import com.example.dao.tables.TeamsUsers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TeamUser(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TeamUser>(TeamsUsers)

    var team by TeamsUsers.team
    var user by TeamsUsers.user
}