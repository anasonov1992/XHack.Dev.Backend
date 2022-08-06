package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.Request
import com.example.dao.entities.Team
import com.example.dao.interfaces.TeamsDataSource
import com.example.dao.mappers.toTeamDto
import com.example.dao.tables.Requests
import com.example.dao.tables.Teams
import com.example.data.models.TeamDto
import com.example.utils.MocksGenerator
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class TeamsDataSourceImpl : TeamsDataSource {
    // Using SQL DSL
//    private fun resultRowToNode(row: ResultRow) = TeamDto(
//        id = row[Teams.id].value,
//        name = row[Teams.name],
//        description = row[Teams.description],
//        maxUsersCount = row[Teams.maxUsersCount],
//        avatarUrl = row[Teams.avatarUrl]
//    )

    //Use DAO
    override suspend fun getTeams(userId: Int): List<TeamDto> = dbQuery {
        //FIXME find { Teams.userId eq userId }.toList()
        Team.all().map { it.toTeamDto() }.toList()
    }

    override suspend fun createTeam(team: TeamDto): TeamDto = dbQuery {
        // Using SQL DSL
//        val insertStatement = Teams.insert {
//            it[name] = team.name
//            it[description] = description
//            it[maxUsersCount] = maxUsersCount
//            it[avatarUrl] = avatarUrl
//        }
//        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToNode)

        //Use DAO
        Team.new {
            name = team.name
            description = team.description
            maxUsersCount = team.maxUsersCount
            avatarUrl = team.avatarUrl ?: MocksGenerator.getRandomImageUrl(200, 200) //FIXME
        }.toTeamDto()
    }
}