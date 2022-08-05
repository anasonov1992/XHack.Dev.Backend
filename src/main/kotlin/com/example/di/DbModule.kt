package com.example.di

import com.example.dao.HackathonsDataSourceImpl
import com.example.dao.RequestsDataSourceImpl
import com.example.dao.TeamsDataSourceImpl
import com.example.dao.UsersDataSourceImpl
import com.example.dao.interfaces.HackathonsDataSource
import com.example.dao.interfaces.RequestsDataSource
import com.example.dao.interfaces.TeamsDataSource
import com.example.dao.interfaces.UsersDataSource
import org.koin.dsl.module

val dbModule = module {
    single<UsersDataSource> { UsersDataSourceImpl() }
    single<TeamsDataSource> { TeamsDataSourceImpl() }
    single<RequestsDataSource> { RequestsDataSourceImpl() }
    single<HackathonsDataSource> { HackathonsDataSourceImpl() }
}