package com.example.dao.interfaces

import com.example.dao.entities.Team
import com.example.data.models.TeamDto

interface TeamsDataSource {
    suspend fun createTeam(team: TeamDto): Team
}