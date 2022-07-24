package com.example.di

import com.example.security.hashing.HashingService
import com.example.security.hashing.SHA256HashingService
import com.example.security.token.JwtTokenService
import com.example.security.token.TokenService
import org.koin.dsl.module

val authModule = module {
    single<TokenService> { JwtTokenService() }
    single<HashingService> { SHA256HashingService() }
}