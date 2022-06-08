package com.marazmone.crypton.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

// Android
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        datasourceModule,
    )
}

// IOS
fun initKoin() = initKoin {}