package com.example.features.auth

import com.example.dao.interfaces.UsersDataSource
import com.example.data.models.UserDto
import com.example.data.requests.AuthRequest
import com.example.data.requests.RegisterUserRequest
import com.example.data.responses.TokenResponse
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

    private val userDataSource by inject<UsersDataSource>(UsersDataSource::class.java)
    private val hashingService by inject<HashingService>(HashingService::class.java)
    private val tokenService by inject<TokenService>(TokenService::class.java)

    suspend fun login(tokenConfig: TokenConfig) {
        val request = call.receiveOrNull<AuthRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@login
        }

        val user = userDataSource.getByEmail(request.email)
        if (user == null) {
            call.respond(HttpStatusCode.Conflict, "Incorrect email or password")
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
            call.respond(HttpStatusCode.Conflict, "Incorrect email or password")
            return@login
        }

        call.respond(HttpStatusCode.OK, getTokenResponse(tokenConfig, user.id.toString()))
    }

    suspend fun register(tokenConfig: TokenConfig) {
        val request = call.receiveOrNull<RegisterUserRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@register
        }

        val areFieldsBlank = request.firstName.isBlank()
                || request.lastName.isBlank()
                || request.email.isBlank()
                || request.password.isBlank()
        val isPwTooShort = request.password.length < minPasswordLength

        if (areFieldsBlank || isPwTooShort) {
            call.respond(HttpStatusCode.Conflict, "Incorrect email or password")
            return@register
        }

        if (userDataSource.getByEmail(request.email) != null) {
            call.respond(HttpStatusCode.Conflict, "Email already exists. Please specify another one.")
            return@register
        }

        val saltedHash = hashingService.generateHash(request.password)
        val user = UserDto(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        if (userDataSource.createUser(user) == null)  {
            call.respond(HttpStatusCode.Conflict, "An error happened during account creation.")
            return@register
        }

        call.respond(HttpStatusCode.OK, getTokenResponse(tokenConfig, user.id.toString()))
    }

    private fun getTokenResponse(tokenConfig: TokenConfig, tokenClaimValue: String): TokenResponse {
        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = tokenClaimValue
            )
        )
        return TokenResponse(token)
    }
}