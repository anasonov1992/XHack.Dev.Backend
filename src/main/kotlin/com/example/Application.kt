package com.example

import com.example.dao.DatabaseFactory
import com.example.di.authModule
import com.example.di.dbModule
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import com.example.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    startKoin { modules(authModule, dbModule) }
    DatabaseFactory.init()
    EngineMain.main(args)
}

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    org.koin.dsl.module {
        single {
            TokenConfig(
                issuer = environment.config.property("jwt.issuer").getString(),
                audience = environment.config.property("jwt.audience").getString(),
                expiresIn = 365L * 1000L * 60L * 60L * 24L,
                secret = environment.config.property("jwt.secret").getString()
            )
        }
    }

    configureRouting()
    configureSecurity()
    configureSerialization()
    configureMonitoring()
}

