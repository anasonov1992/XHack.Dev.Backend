package com.example.dao.entities

import com.example.dao.tables.Requests
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Request(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Request>(Requests)

    var invitedUserId by Requests.invitedUserId
    var fromUser by User optionalReferencedOn Requests.fromUser
    var fromTeam by Team optionalReferencedOn Requests.fromTeam
    var type by Requests.type
    var isCanceled by Requests.isCanceled
}