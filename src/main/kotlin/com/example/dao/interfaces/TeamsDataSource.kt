package com.example.dao.interfaces

import com.example.data.models.AddUserToTeamDto
import com.example.data.models.TeamDto

interface TeamsDataSource {
    suspend fun getTeams(userId: Int): List<TeamDto>
    suspend fun createTeam(team: TeamDto): TeamDto
    suspend fun addUserToTeam(data: AddUserToTeamDto)
}