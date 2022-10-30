package com.example.plugins

import com.example.routes.*
import com.example.routes.auth.login
import com.example.routes.auth.register
import com.example.routes.blackcards.createCardArt
import com.example.routes.blackcards.createFraction
import com.example.routes.blackcards.getCardArts
import com.example.routes.blackcards.getFractions
import com.example.routes.files.upload
import com.example.utils.configureToken
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    val tokenConfig = configureToken()

    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
        get("/test") {
            call.respondText("Testing...")
        }

        login(tokenConfig)
        register(tokenConfig)

        authenticate("auth-jwt") {
            getRequests()
            createRequest()
            getTeams()
            createTeam()
            addUserToTeam()
            getUsers()
            getHackathons()
            createHackathon()
            getTags()
            addTag()
            getChats()
            getChatMessages()
            createP2PChat()
            createTeamChat()
            sendMessage()
            deleteChat()
            upload(tokenConfig)

            // Black Cards
            getFractions()
            createFraction()
            getCardArts()
            createCardArt()
        }

        static {
            staticRootFolder = File("files")
            static("cards") {
                files(".")
            }
        }
    }
}
