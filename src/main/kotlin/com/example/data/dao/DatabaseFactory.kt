package com.example.data.dao

import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val driverClassName = config.property("ktor.database.driverClassName").getString()
        val jdbcURL = config.property("ktor.database.jdbcURL").getString()
        val username = config.property("ktor.database.user").getString()
        val password = config.property("ktor.database.password").getString()
        val defaultDatabase = config.property("ktor.database.database").getString()
        val database = Database.connect(
            url = "$jdbcURL/$defaultDatabase",
            driver = driverClassName,
            user = username,
            password = password
        )

//        transaction(database) {
//            SchemaUtils.create(Nodes)
//            SchemaUtils.create(Users)
//        }
    }
}