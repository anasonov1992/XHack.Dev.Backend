package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.Team
import com.example.dao.interfaces.TeamsDataSource
import com.example.data.models.TeamDto

class TeamsDataSourceImpl : TeamsDataSource {
    // Using SQL DSL
//    private fun resultRowToNode(row: ResultRow) = TeamDto(
//        id = row[Teams.id].value,
//        name = row[Teams.name],
//        description = row[Teams.description],
//        maxUsersCount = row[Teams.maxUsersCount],
//        avatarUrl = row[Teams.avatarUrl]
//    )

    override suspend fun createTeam(team: TeamDto): Team = dbQuery {
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
            avatarUrl = team.avatarUrl
        }
    }
}