package com.example.dao.mappers

import com.example.dao.entities.Hackathon
import com.example.dao.entities.Tag
import com.example.data.models.HackathonDto
import com.example.data.models.TagDto

fun Hackathon.toHackathonDto(): HackathonDto =
    HackathonDto(id.value, name, description, isOnline, location, siteUrl, avatarUrl, isBookmarked, tags.map { it.toTagDto() })

fun Tag.toTagDto(): TagDto = TagDto(id.value, name)