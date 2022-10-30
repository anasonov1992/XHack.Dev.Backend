package com.example.controllers.blackcards

import com.example.dao.interfaces.blackcards.FractionsDataSource
import com.example.data.models.blackcards.FractionDto
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class FractionsController(private val call: ApplicationCall) {
    private val fractionsDataSource by KoinJavaComponent.inject<FractionsDataSource>(FractionsDataSource::class.java)

    suspend fun getFractions() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        call.respond(HttpStatusCode.OK, fractionsDataSource.getFractions())
    }

    suspend fun createFraction() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val fraction = call.receiveOrNull<FractionDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        call.respond(HttpStatusCode.OK, fractionsDataSource.createFraction(fraction))
    }
}