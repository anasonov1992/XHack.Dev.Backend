package com.example.utils

import com.example.security.token.TokenConfig
import io.ktor.server.application.*

fun Application.configureToken() =
    TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = environment.config.property("jwt.secret").getString()
    )