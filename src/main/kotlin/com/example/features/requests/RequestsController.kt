package com.example.features.requests

import com.example.dao.interfaces.RequestsDataSource
import com.example.dao.mappers.toTeamRequestDto
import com.example.dao.mappers.toUserRequestDto
import com.example.data.responses.RequestResponseDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class RequestsController(private val call: ApplicationCall) {
    private val requestsDataSource by KoinJavaComponent.inject<RequestsDataSource>(RequestsDataSource::class.java)

    suspend fun getRequests() {
        val response = requestsDataSource.getRequests().run {
            RequestResponseDto(
                fromTeams = filter { it.team != null }.map { it.toTeamRequestDto() },
                fromUsers = filter { it.user != null }.map { it.toUserRequestDto() }
            )
        }

        call.respond(HttpStatusCode.OK, response)
    }
}