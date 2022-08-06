package com.example.controllers

import com.example.dao.interfaces.TeamsDataSource
import com.example.data.models.TeamDto
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class TeamsController(private val call: ApplicationCall) {
    private val teamsDataSource by KoinJavaComponent.inject<TeamsDataSource>(TeamsDataSource::class.java)

    suspend fun getTeams() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, "User is unauthorized")
            return
        }

        call.respond(HttpStatusCode.OK, teamsDataSource.getTeams(userId.toInt()))
    }

    suspend fun createTeam() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, "User is unauthorized")
            return
        }

        val team = call.receiveOrNull<TeamDto>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        call.respond(HttpStatusCode.OK, teamsDataSource.createTeam(team))
    }
}