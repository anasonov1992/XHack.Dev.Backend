package com.example.controllers.blackcards

import com.example.dao.interfaces.blackcards.CardsDataSource
import com.example.data.models.blackcards.CreateCardArtDto
import com.example.data.models.blackcards.RankDto
import com.example.data.models.blackcards.SpellTypeDto
import com.example.data.models.blackcards.UnitClassDto
import com.example.utils.Constants
import com.example.utils.DbResult
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class SpellTypesController(private val call: ApplicationCall) {
    private val cardsDataSource by KoinJavaComponent.inject<CardsDataSource>(CardsDataSource::class.java)

    suspend fun getSpellTypes() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        call.respond(HttpStatusCode.OK, cardsDataSource.getSpellTypes())
    }

    suspend fun createSpellType() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val spellType = call.receiveOrNull<SpellTypeDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        when (val dbResult = cardsDataSource.createSpellType(spellType)) {
            is DbResult.Success -> call.respond(HttpStatusCode.OK, dbResult.data)
            else -> call.respond(HttpStatusCode.Conflict, "Error")
        }
    }
}