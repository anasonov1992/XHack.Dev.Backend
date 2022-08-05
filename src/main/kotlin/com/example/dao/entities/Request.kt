package com.example.dao.entities

import com.example.dao.tables.Requests
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Request(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Request>(Requests)

    var user by User optionalReferencedOn Requests.user
    var team by Team optionalReferencedOn Requests.team
    var type by Requests.type
    var isCanceled by Requests.isCanceled
}