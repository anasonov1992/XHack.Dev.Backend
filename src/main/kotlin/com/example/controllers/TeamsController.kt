package com.example.controllers

import com.example.dao.interfaces.TeamsDataSource
import com.example.data.models.AddUserToTeamDto
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
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        call.respond(HttpStatusCode.OK, teamsDataSource.getTeams(userId.toInt()))
    }

    suspend fun createTeam() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val team = call.receiveOrNull<TeamDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        call.respond(HttpStatusCode.OK, teamsDataSource.createTeam(team))
    }

    suspend fun addUserToTeam() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val request = call.receiveOrNull<AddUserToTeamDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        val data = teamsDataSource.addUserToTeam(request)
        if (data == null) {
            call.respond(HttpStatusCode.BadRequest, "User or team are not found")
        }

        call.respond(HttpStatusCode.OK)
    }
}