package com.example.controllers

import com.example.dao.interfaces.UsersDataSource
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class UsersController(private val call: ApplicationCall) {
    private val usersDataSource by KoinJavaComponent.inject<UsersDataSource>(UsersDataSource::class.java)

    suspend fun getUsers() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, "User is unauthorized")
            return
        }

        call.respond(HttpStatusCode.OK, usersDataSource.getUsers())
    }
}