package com.example.controllers

import com.example.dao.interfaces.HackathonsDataSource
import com.example.data.models.HackathonDto
import com.example.data.models.TagDto
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class HackathonsController(private val call: ApplicationCall) {
    private val hackathonsDataSource by KoinJavaComponent.inject<HackathonsDataSource>(HackathonsDataSource::class.java)

    suspend fun getHackathons() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, "User is unauthorized")
            return
        }

        call.respond(HttpStatusCode.OK, hackathonsDataSource.getHackathons())
    }

    suspend fun createHackathon() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, "User is unauthorized")
            return
        }

        val hackathon = call.receiveOrNull<HackathonDto>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        call.respond(HttpStatusCode.OK, hackathonsDataSource.createHackathon(hackathon))
    }


    suspend fun getTags() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, "User is unauthorized")
            return
        }

        call.respond(HttpStatusCode.OK, hackathonsDataSource.getTags())
    }

    suspend fun addTag() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, "User is unauthorized")
            return
        }

        val tag = call.receiveOrNull<TagDto>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        call.respond(HttpStatusCode.OK, hackathonsDataSource.addTag(tag))
    }
}