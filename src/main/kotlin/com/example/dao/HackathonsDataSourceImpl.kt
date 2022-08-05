package com.example.dao

import com.example.dao.entities.Hackathon
import com.example.dao.interfaces.HackathonsDataSource
import com.example.data.models.HackathonDto
import com.example.data.requests.PagingRequest

class HackathonsDataSourceImpl: HackathonsDataSource {
    override suspend fun getHackathons(request: PagingRequest): List<Hackathon> {
        //TODO
        return emptyList()
    }

    override suspend fun createHackathon(request: HackathonDto): Hackathon? {
        //TODO
        return null
    }
}