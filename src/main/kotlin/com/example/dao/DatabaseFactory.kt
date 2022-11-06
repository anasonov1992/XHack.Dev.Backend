package com.example.dao

import com.example.dao.tables.*
import com.example.dao.tables.blackcards.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() { //config: ApplicationConfig

//        val driverClassName = config.property("ktor.database.driverClassName").getString()
//        val jdbcURL = config.property("ktor.database.jdbcURL").getString()
//        val username = config.property("ktor.database.user").getString()
//        val password = config.property("ktor.database.password").getString()
//        val defaultDatabase = config.property("ktor.database.database").getString()
//        val database = Database.connect(
//            url = "$jdbcURL/$defaultDatabase",
//            driver = driverClassName,
//            user = username,
//            password = password
//        )

        val config = HikariConfig("hikari.properties")
        val dataSource = HikariDataSource(config)
        val database = Database.connect(dataSource)

        transaction(database) {
            //FIXME use
//            SchemaUtils.drop(ChatMessages, ChatsUsers, TeamsUsers, Requests, Users)
            SchemaUtils.create(
                Users,
                Teams,
                Requests,
                Hackathons,
                Tags,
                HackathonsTags,
                Chats,
                ChatMessages,
                ChatsUsers,
                TeamsUsers,
                Files,

                // Black Cards
                Fractions,
                Cards,
                Ranks,
                UnitClasses,
                CardUnits
            )
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}