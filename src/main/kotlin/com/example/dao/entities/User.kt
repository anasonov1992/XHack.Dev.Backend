package com.example.dao.entities

import com.example.dao.tables.TeamsUsers
import com.example.dao.tables.Users
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var username by Users.username
    var email by Users.email
    var password by Users.password
    var salt by Users.salt
    var teams by Team via TeamsUsers
}

