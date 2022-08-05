package com.example.features.hackathons

import com.example.dao.interfaces.RequestsDataSource
import com.example.data.requests.PagingRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class HackathonsController(private val call: ApplicationCall) {
    private val requestsDataSource by KoinJavaComponent.inject<RequestsDataSource>(RequestsDataSource::class.java)

    suspend fun getHackathons() {
        val request = call.receiveOrNull<PagingRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@getHackathons
        }

        //TODO

        call.respond(HttpStatusCode.OK)
    }
}