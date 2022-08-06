package com.example.controllers

import com.example.dao.interfaces.RequestsDataSource
import com.example.dao.mappers.toRequestDto
import com.example.dao.mappers.toTeamRequestDto
import com.example.dao.mappers.toUserRequestDto
import com.example.data.models.InviteUserDto
import com.example.data.responses.RequestResponseDto
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class RequestsController(private val call: ApplicationCall) {
    private val requestsDataSource by KoinJavaComponent.inject<RequestsDataSource>(RequestsDataSource::class.java)

    suspend fun getRequests() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, "User is unauthorized")
            return
        }

        println("UserId: $userId")

        val response = requestsDataSource.getRequests(userId.toInt()).run {
            RequestResponseDto(
                fromTeams = filter { it.team != null },
                fromUsers = filter { it.user != null }
            )
        }

        call.respond(HttpStatusCode.OK, response)
    }

    suspend fun createRequest() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, "User is unauthorized")
            return
        }

        val request = call.receiveOrNull<InviteUserDto>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        val response = requestsDataSource.createRequest(userId.toInt(), request)
        if (response == null) {
            call.respond(HttpStatusCode.BadRequest, "User or team is not found")
            return
        }

        call.respond(HttpStatusCode.OK, response)
    }
}