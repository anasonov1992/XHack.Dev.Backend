package com.example.features.auth

import com.example.dao.interfaces.UserDataSource
import com.example.dao.models.User
import com.example.data.requests.AuthRequest
import com.example.data.responses.AuthResponse
import com.example.security.hashing.HashingService
import com.example.security.hashing.SaltedHash
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.apache.commons.codec.digest.DigestUtils
import org.koin.java.KoinJavaComponent.inject

class AuthController(private val call: ApplicationCall) {
    private val minPasswordLength = 8

    private val userDataSource by inject<UserDataSource>(UserDataSource::class.java)
    private val hashingService by inject<HashingService>(HashingService::class.java)
    private val tokenService by inject<TokenService>(TokenService::class.java)

    suspend fun login(tokenConfig: TokenConfig) {
        val request = call.receiveOrNull<AuthRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@login
        }

        val user = userDataSource.getByUsername(request.username)
        if (user == null) {
            call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
            return@login
        }

        val isValidPassword = hashingService.verify(
            value = request.password,
            saltedHash = SaltedHash(
                hash = user.password,
                salt = user.salt
            )
        )
        if (!isValidPassword) {
            println("Entered hash: ${DigestUtils.sha256Hex("${user.salt}${request.password}")}, Hashed PW: ${user.password}")
            call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
            return@login
        }

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = user.id.toString()
            )
        )

        call.respond(status = HttpStatusCode.OK, message = AuthResponse(token))
    }

    suspend fun register(tokenConfig: TokenConfig) {
        val request = call.receiveOrNull<AuthRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@register
        }

        val areFieldsBlank = request.username.isBlank() || request.password.isBlank()
        val isPwTooShort = request.password.length < minPasswordLength
        if(areFieldsBlank || isPwTooShort) {
            call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
            return@register
        }

        val saltedHash = hashingService.generateHash(request.password)
        val user = User(
            username = request.username,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        val wasAcknowledged = userDataSource.createUser(user)
        if(wasAcknowledged == null)  {
            call.respond(HttpStatusCode.Conflict)
            return@register
        }

        call.respond(HttpStatusCode.OK)
    }
}