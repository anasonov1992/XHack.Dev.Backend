package com.example.dao.interfaces

import com.example.dao.entities.Hackathon
import com.example.data.models.HackathonDto
import com.example.data.requests.PagingRequest

interface HackathonsDataSource {
    suspend fun getHackathons(request: PagingRequest): List<Hackathon>
    suspend fun createHackathon(request: HackathonDto): Hackathon?
}