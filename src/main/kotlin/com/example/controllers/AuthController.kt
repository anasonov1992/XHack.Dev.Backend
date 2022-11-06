package com.example.controllers

import com.example.dao.interfaces.UsersDataSource
import com.example.data.models.RegisterUserDto
import com.example.data.requests.AuthRequestDto
import com.example.data.requests.RegisterUserRequestDto
import com.example.data.responses.TokenResponse
import com.example.security.hashing.HashingService
import com.example.security.hashing.SaltedHash
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.apache.commons.codec.digest.DigestUtils
import org.koin.java.KoinJavaComponent.inject

class AuthController(private val call: ApplicationCall) {
    private val minPasswordLength = 8

    private val userDataSource by inject<UsersDataSource>(UsersDataSource::class.java)
    private val hashingService by inject<HashingService>(HashingService::class.java)
    private val tokenService by inject<TokenService>(TokenService::class.java)

    suspend fun login(tokenConfig: TokenConfig) {
        val request = call.receiveOrNull<AuthRequestDto>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@login
        }

        val user = userDataSource.getByEmail(request.email)
        if (user == null) {
            call.respond(HttpStatusCode.Conflict, "Incorrect email or password")
            return
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
            call.respond(HttpStatusCode.Conflict, "Incorrect email or password")
            return
        }

        call.respond(HttpStatusCode.OK, getTokenResponse(tokenConfig, user.id.toString()))
    }

    suspend fun register(tokenConfig: TokenConfig) {
        val request = call.receiveOrNull<RegisterUserRequestDto>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@register
        }

        val areFieldsBlank = request.username.isBlank()
                || request.email.isBlank()
                || request.password.isBlank()
        val isPwTooShort = request.password.length < minPasswordLength

        if (areFieldsBlank || isPwTooShort) {
            call.respond(HttpStatusCode.Conflict, "Incorrect email or password")
            return
        }

        if (userDataSource.getByEmail(request.email) != null) {
            call.respond(HttpStatusCode.Conflict, "Email already exists. Please specify another one.")
            return
        }

        val saltedHash = hashingService.generateHash(request.password)
        val user = userDataSource.registerUser(
            RegisterUserDto(
                username = request.username,
                email = request.email,
                password = saltedHash.hash,
                salt = saltedHash.salt
            )
        )

        call.respond(HttpStatusCode.OK, getTokenResponse(tokenConfig, user.id.toString()))
    }

    private fun getTokenResponse(tokenConfig: TokenConfig, tokenClaimValue: String): TokenResponse {
        println("tokenClaimValue: $tokenClaimValue")

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = Constants.USER_CLAIM_NAME,
                value = tokenClaimValue
            )
        )
        return TokenResponse(token)
    }
}