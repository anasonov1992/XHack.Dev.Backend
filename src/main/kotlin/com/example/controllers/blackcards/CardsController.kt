package com.example.controllers.blackcards

import com.example.dao.interfaces.blackcards.CardsDataSource
import com.example.data.models.blackcards.CardUnitDto
import com.example.data.models.blackcards.CreateCardArtDto
import com.example.data.models.blackcards.CreateCardUnitDto
import com.example.data.requests.SearchPagingRequestDto
import com.example.data.requests.blackcards.CardsPagingRequestDto
import com.example.utils.Constants
import com.example.utils.DbResult
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

        val request = call.receiveOrNull<SearchPagingRequestDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        call.respond(HttpStatusCode.OK, cardsDataSource.getCardArts(request))
    }

    suspend fun getCardArtDetail() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val id = call.parameters["id"] ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        when (val dbResult = cardsDataSource.getCardArtDetail(id.toInt())) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, "Art is not found")
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict, "Conflict error")
            is DbResult.Success ->  call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }

    suspend fun createCardArt() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val cardArt = call.receiveOrNull<CreateCardArtDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        when (val dbResult = cardsDataSource.createCardArt(cardArt)) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, "Fraction is not found")
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict, "Conflict error")
            is DbResult.Success ->  call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }

    suspend fun updateCardArt() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val cardArt = call.receiveOrNull<CreateCardArtDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        when (val dbResult = cardsDataSource.updateCardArt(cardArt)) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, "Art is not found")
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict, "Conflict error")
            is DbResult.Success ->  call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }

    suspend fun getCardUnits() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val request = call.receiveOrNull<CardsPagingRequestDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        call.respond(HttpStatusCode.OK, cardsDataSource.getCardUnits(request))
    }

    suspend fun createCardUnit() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val cardUnit = call.receiveOrNull<CreateCardUnitDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        when (val dbResult = cardsDataSource.createCardUnit(cardUnit)) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, "Fraction or rank are not found")
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict, "Conflict error")
            is DbResult.Success ->  call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }

    suspend fun updateCardUnit() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val cardUnit = call.receiveOrNull<CreateCardUnitDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        when (val dbResult = cardsDataSource.updateCardUnit(cardUnit)) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, "Unit or fraction/rank are not found")
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict, "Conflict error")
            is DbResult.Success ->  call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }
}

