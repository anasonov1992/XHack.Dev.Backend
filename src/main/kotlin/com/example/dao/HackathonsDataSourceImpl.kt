package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.Hackathon
import com.example.dao.entities.Tag
import com.example.dao.interfaces.HackathonsDataSource
import com.example.dao.mappers.toHackathonDto
import com.example.dao.mappers.toTagDto
import com.example.dao.tables.Tags
import com.example.data.models.HackathonDto
import com.example.data.models.TagDto
import com.example.data.responses.HackathonsResponseDto
import com.example.utils.MocksGenerator
import org.jetbrains.exposed.sql.SizedCollection

class HackathonsDataSourceImpl: HackathonsDataSource {
    override suspend fun getHackathons(): HackathonsResponseDto = dbQuery {
        //Use DAO
       Hackathon.all().map { it.toHackathonDto() }.run {
           HackathonsResponseDto(
               topHackathons = this,
               popularHackathons = this,
               recentHackathons = this
           )
       }
    }

    override suspend fun createHackathon(hackathon: HackathonDto): HackathonDto = dbQuery {
        //Use DAO
        var dbTags = emptyList<Tag>()
        if (hackathon.tags.isNotEmpty()) {
            dbTags = Tag.find { Tags.id inList hackathon.tags.map { it.id } }.toList()
        }

        Hackathon.new {
            name = hackathon.name
            description = hackathon.description
            isOnline = hackathon.isOnline
            location = hackathon.location
//            startDate = hackathon.startDate
//            endDate = hackathon.endDate
            siteUrl = hackathon.siteUrl
            avatarUrl = hackathon.avatarUrl ?: MocksGenerator.getRandomImageUrl() //FIXME
            isBookmarked = hackathon.isBookmarked
            tags = SizedCollection(dbTags)
        }.toHackathonDto()
    }

    override suspend fun getTags(): List<TagDto> = dbQuery {
        //Use DAO
        Tag.all().map { it.toTagDto() }.toList()
    }

    override suspend fun addTag(tag: TagDto): TagDto = dbQuery {
        //Use DAO
        Tag.new {
            name = tag.name
        }.toTagDto()
    }
}