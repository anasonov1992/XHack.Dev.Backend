package com.example.di

import com.example.dao.UserDataSourceImpl
import org.koin.dsl.module

val dbModule = module {
    single { UserDataSourceImpl() }
}