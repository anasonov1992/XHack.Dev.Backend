package com.example.routes

import com.example.data.requests.AuthRequest
import com.example.data.responses.AuthResponse
import com.example.data.models.User
import com.example.data.interfaces.UserDataSource
import com.example.security.hashing.HashingService
import com.example.security.hashing.SaltedHash
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.apache.commons.codec.digest.DigestUtils

fun Route.signUp(
    hashingService: HashingService
//    userDataSource: UserDataSource
) {
//    post("signup") {
//        val request = call.receiveOrNull<AuthRequest>() ?: kotlin.run {
//            call.respond(HttpStatusCode.BadRequest)
//            return@post
//        }
//
//        val areFieldsBlank = request.username.isBlank() || request.password.isBlank()
//        val isPwTooShort = request.password.length < 8
//        if(areFieldsBlank || isPwTooShort) {
//            call.respond(HttpStatusCode.Conflict)
//            return@post
//        }
//
//        val saltedHash = hashingService.generateHash(request.password)
//        val user = User(
//            username = request.username,
//            password = saltedHash.hash,
//            salt = saltedHash.salt
//        )
//        val wasAcknowledged = userDataSource.insertUser(user)
//        if(!wasAcknowledged)  {
//            call.respond(HttpStatusCode.Conflict)
//            return@post
//        }
//
//        call.respond(HttpStatusCode.OK)
//    }
}


fun Route.signIn(
//    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
//    post("signin") {
//        val request = call.receiveOrNull<AuthRequest>() ?: kotlin.run {
//            call.respond(HttpStatusCode.BadRequest)
//            return@post
//        }
//
//        val user = userDataSource.getUserByUsername(request.username)
//        if(user == null) {
//            call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
//            return@post
//        }
//
//        val isValidPassword = hashingService.verify(
//            value = request.password,
//            saltedHash = SaltedHash(
//                hash = user.password,
//                salt = user.salt
//            )
//        )
//        if(!isValidPassword) {
//            println("Entered hash: ${DigestUtils.sha256Hex("${user.salt}${request.password}")}, Hashed PW: ${user.password}")
//            call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
//            return@post
//        }
//
//        val token = tokenService.generate(
//            config = tokenConfig,
//            TokenClaim(
//                name = "userId",
//                value = user.id.toString()
//            )
//        )
//
//        call.respond(
//            status = HttpStatusCode.OK,
//            message = AuthResponse(
//                token = token
//            )
//        )
//    }
}