package com.example

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {
//    val config = HikariConfig("hikari.properties")
//    val dataSource = HikariDataSource(config)
//    Database.connect(dataSource)

    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureRouting()
        configureSerialization()
        configureMonitoring()
        configureSecurity()
    }.start(wait = true)
}
