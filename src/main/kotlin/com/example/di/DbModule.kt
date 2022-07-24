package com.example.di

import com.example.dao.UserDataSourceImpl
import com.example.dao.interfaces.UserDataSource
import org.koin.dsl.module

val dbModule = module {
    single<UserDataSource> { UserDataSourceImpl() }
}