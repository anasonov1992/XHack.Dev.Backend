package com.example.di

import com.example.dao.*
import com.example.dao.interfaces.*
import com.example.dao.interfaces.blackcards.CardsDataSource
import com.example.dao.interfaces.blackcards.FractionsDataSource
import org.koin.dsl.module

val dbModule = module {
    single<UsersDataSource> { UsersDataSourceImpl() }
    single<TeamsDataSource> { TeamsDataSourceImpl() }
    single<RequestsDataSource> { RequestsDataSourceImpl() }
    single<HackathonsDataSource> { HackathonsDataSourceImpl() }
    single<ChatsDataSource> { ChatsDataSourceImpl() }
    single<CardsDataSource> { CardsDataSourceImpl() }
    single<FractionsDataSource> { FractionsDataSourceImpl() }
}