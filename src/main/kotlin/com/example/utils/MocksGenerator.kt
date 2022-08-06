package com.example.utils

object MocksGenerator {
    fun getRandomImageUrl(width: Int = 600, height: Int = 400): String =
        "${Constants.IMAGE_MOCKS_URL}/id/${(1..1000).random()}/$width/$height"
}