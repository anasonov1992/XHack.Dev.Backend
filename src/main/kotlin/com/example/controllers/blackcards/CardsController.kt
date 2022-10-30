package com.example.controllers.blackcards

import com.example.dao.interfaces.blackcards.CardsDataSource
import com.example.data.models.blackcards.CardArtDto
import com.example.data.requests.PagingRequestDto
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class CardsController(private val call: ApplicationCall) {
    private val cardsDataSource by KoinJavaComponent.inject<CardsDataSource>(CardsDataSource::class.java)

    suspend fun getCardArts() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val request = call.receiveOrNull<PagingRequestDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        call.respond(HttpStatusCode.OK, cardsDataSource.getCardArts(request))
    }

    suspend fun createCardArt() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val cardArt = call.receiveOrNull<CardArtDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        call.respond(HttpStatusCode.OK, cardsDataSource.createCardArt(cardArt))
    }
}
