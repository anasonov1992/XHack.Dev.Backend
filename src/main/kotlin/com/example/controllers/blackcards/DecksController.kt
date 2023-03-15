package com.example.controllers.blackcards

import com.example.dao.interfaces.blackcards.DeckDataSource
import com.example.data.models.blackcards.CreateDeckDto
import com.example.data.requests.blackcards.DeckCardsRequestDto
import com.example.data.requests.blackcards.FractionCardsRequestDto
import com.example.utils.Constants
import com.example.utils.DbResult
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class DecksController(private val call: ApplicationCall) {
    private val decksDataSource by KoinJavaComponent.inject<DeckDataSource>(DeckDataSource::class.java)


    suspend fun getFractionCards() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val request = call.receiveOrNull<FractionCardsRequestDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        call.respond(HttpStatusCode.OK, decksDataSource.getFractionCards(request.fractionId))
    }

    suspend fun getDeckCards() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val request = call.receiveOrNull<DeckCardsRequestDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        call.respond(HttpStatusCode.OK, decksDataSource.getDeckCards(request.deckId))
    }

    suspend fun createDeck() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val deck = call.receiveOrNull<CreateDeckDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        when (val dbResult = decksDataSource.createDeck(deck)) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, "Fraction is not found")
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict, "Conflict error")
            is DbResult.Success -> call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }
}