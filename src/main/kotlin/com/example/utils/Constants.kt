package com.example.utils

object Constants {
    // URLs
    const val BASE_URL = "https://xhack-backend.herokuapp.com"
    const val UPLOAD_PATH = "uploads"
    const val USER_CLAIM_NAME = "userId"
    const val IMAGE_MOCKS_URL = "https://picsum.photos"

    // Errors
    const val UNAUTHORIZED = "User is unauthorized"
    const val INVALID_REQUEST = "Invalid request"
    const val FILE_NOT_FOUND = "File not found"

    const val TEAM_NOT_FOUND = "Team is not found or empty"
    const val CHAT_NOT_FOUND = "Chat is not found"
    const val P2P_CHAT_EXISTS = "P2P chat already exists"
    const val TEAM_CHAT_EXISTS = "Team chat already exists"
    const val CHAT_OR_SENDER_NOT_FOUND = "Chat or sender are not found"

    // Texts
    const val UNKNOWN_TEXT: String = "Unknown"
}