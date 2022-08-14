package com.example.plugins

import com.example.routes.*
import com.example.routes.auth.login
import com.example.routes.auth.register
import com.example.routes.files.upload
import com.example.utils.configureToken
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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
        }
    }
}
