package com.example.di

import com.example.security.hashing.SHA256HashingService
import com.example.security.token.JwtTokenService
import org.koin.dsl.module

val authModule = module {
    single { JwtTokenService() }
    single { SHA256HashingService() }
}