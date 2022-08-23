package com.example.dao.interfaces

import com.example.data.models.AddUserToTeamDto
import com.example.data.models.TeamDto
import com.example.data.requests.PagingRequestDto

interface TeamsDataSource {
    suspend fun getTeams(userId: Int, request: PagingRequestDto): List<TeamDto>
    suspend fun createTeam(team: TeamDto): TeamDto
    suspend fun addUserToTeam(data: AddUserToTeamDto)
}