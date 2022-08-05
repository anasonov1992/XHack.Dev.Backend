package com.example.features.requests

import com.example.dao.interfaces.UsersDataSource
import com.example.data.requests.PagingRequest
import com.example.data.responses.RequestResponseDto
import com.example.security.token.TokenConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class RequestsController(private val call: ApplicationCall) {
    private val userDataSource by KoinJavaComponent.inject<UsersDataSource>(UsersDataSource::class.java)

    suspend fun getRequests(tokenConfig: TokenConfig) {
        val request = call.receiveOrNull<PagingRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@getRequests
        }



        call.respond(HttpStatusCode.OK, RequestResponseDto())
    }
}