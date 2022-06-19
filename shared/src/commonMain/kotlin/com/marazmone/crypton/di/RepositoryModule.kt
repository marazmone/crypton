package com.marazmone.crypton.di

import com.marazmone.crypton.data.repository.CurrencyRepositoryImpl
import com.marazmone.crypton.domain.repository.CurrencyRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
            remote = get(),
            cache = get(),
            responseToEntityMapper = get(qualifier = named("1")),
            entityToListItemMapper = get(qualifier = named("2"))
        )
    }
}