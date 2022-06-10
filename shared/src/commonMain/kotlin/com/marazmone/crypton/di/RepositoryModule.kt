package com.marazmone.crypton.di

import com.marazmone.crypton.data.repository.CurrencyRepositoryImpl
import com.marazmone.crypton.domain.repository.CurrencyRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::CurrencyRepositoryImpl) { bind<CurrencyRepository>() }
}