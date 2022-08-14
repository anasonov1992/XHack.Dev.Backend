package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.Team
import com.example.dao.entities.User
import com.example.dao.interfaces.TeamsDataSource
import com.example.dao.mappers.toTeamDto
import com.example.data.models.AddUserToTeamDto
import com.example.data.models.TeamDto
import com.example.utils.MocksGenerator
import org.jetbrains.exposed.sql.SizedCollection

class TeamsDataSourceImpl : TeamsDataSource {
    override suspend fun getTeams(userId: Int): List<TeamDto> = dbQuery {
        //FIXME find { Teams.userId eq userId }.toList()
        Team.all().map { it.toTeamDto() }.toList()
    }

    override suspend fun createTeam(team: TeamDto): TeamDto = dbQuery {
        Team.new {
            name = team.name
            description = team.description
            maxUsersCount = team.maxUsersCount
            avatarUrl = team.avatarUrl ?: MocksGenerator.getRandomImageUrl(200, 200) //FIXME
        }.toTeamDto()
    }

    override suspend fun addUserToTeam(data: AddUserToTeamDto) {
        dbQuery {
            val dbTeam = Team.findById(data.teamId) ?: return@dbQuery null
            val dbUser = User.findById(data.userId) ?: return@dbQuery null
            if (dbTeam.users.contains(dbUser))
                return@dbQuery null

            val updatedUsers = mutableListOf(dbUser)
            updatedUsers.addAll(dbTeam.users)
            dbTeam.users = SizedCollection(updatedUsers)
        }
    }
}