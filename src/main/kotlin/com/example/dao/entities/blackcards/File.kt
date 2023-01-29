package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.Files
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class File(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<File>(Files)

    var guid by Files.guid
    var name by Files.name
    var extension by Files.extension
    var path by Files.path
    var fileBinary by Files.fileBinary
    var fraction by Fraction optionalReferencedOn Files.fraction
}