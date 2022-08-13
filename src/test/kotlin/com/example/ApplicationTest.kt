package com.example

import com.example.dao.DatabaseFactory
import com.example.data.requests.AuthRequestDto
import com.example.di.authModule
import com.example.di.dbModule
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import org.koin.core.context.startKoin
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        startKoin { modules(authModule, dbModule) }
        DatabaseFactory.init()

        application {
            configureRouting()
            configureSecurity()
            configureSerialization()
        }

        val client = createClient {
            this@testApplication.install(ContentNegotiation) {
                json()
            }
        }

        loginTest(client)
    }

    private suspend fun ApplicationTestBuilder.helloWorldTest() {
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello, World!", bodyAsText())
        }
    }

    private suspend fun ApplicationTestBuilder.loginTest(client: HttpClient) {
        client.post("/login") {
            contentType(ContentType.Application.Json)
            setBody(AuthRequestDto("tony", "power"))
        }.apply {
            assertEquals(HttpStatusCode.Conflict, status)
        }
    }
}