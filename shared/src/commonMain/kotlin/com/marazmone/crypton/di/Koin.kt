package com.marazmone.crypton.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

// Android
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        datasourceModule,
        repositoryModule,
        currencyUseCaseModule,
        currencyMapperModule,
    )
}

// IOS
fun initKoin() = initKoin {}