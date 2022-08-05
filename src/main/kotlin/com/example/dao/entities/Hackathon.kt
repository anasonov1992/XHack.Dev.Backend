package com.example.dao.entities

import com.example.dao.tables.Hackathons
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Hackathon(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Hackathon>(Hackathons)

}