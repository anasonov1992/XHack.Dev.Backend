package com.example.dao.entities

import com.example.dao.tables.Hackathons
import com.example.dao.tables.HackathonsTags
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Hackathon(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Hackathon>(Hackathons)

    var name by Hackathons.name
    var description by Hackathons.description
    var isOnline by Hackathons.isOnline
    var location by Hackathons.location
    //FIXME implement dates
//    var startDate by Hackathons.startDate
//    var endDate by Hackathons.endDate
    var siteUrl by Hackathons.siteUrl
    var avatarUrl by Hackathons.avatarUrl
    var isBookmarked by Hackathons.isBookmarked
    var tags by Tag via HackathonsTags
}