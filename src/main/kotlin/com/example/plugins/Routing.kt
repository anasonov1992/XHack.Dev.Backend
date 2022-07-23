package com.example.plugins

import com.example.data.interfaces.UserDataSource
import com.example.routes.signIn
import com.example.routes.signUp
import com.example.security.hashing.HashingService
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
//    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {
        get("/") {
            call.respondText("Hello, World")
        }
        get("/test") {
            call.respondText("Testing...")
        }
        signIn(hashingService, tokenService, tokenConfig) //userDataSource,
        signUp(hashingService) //, userDataSource
    }
}
