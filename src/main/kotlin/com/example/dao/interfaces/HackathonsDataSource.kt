package com.example.dao.interfaces

import com.example.data.models.HackathonDto
import com.example.data.models.TagDto
import com.example.data.requests.PagingRequest

interface HackathonsDataSource {
    suspend fun getHackathons(request: PagingRequest): List<HackathonDto>
    suspend fun createHackathon(request: HackathonDto): HackathonDto

    suspend fun getTags() : List<TagDto>

    suspend fun addTag(tag: TagDto) : TagDto
}