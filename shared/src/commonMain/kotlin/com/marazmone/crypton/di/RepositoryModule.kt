package com.marazmone.crypton.di

import com.marazmone.crypton.data.repository.CurrencyRepositoryImpl
import com.marazmone.crypton.di.MapperNamed.CURRENCY_ENTITY_TO_DETAIL
import com.marazmone.crypton.di.MapperNamed.CURRENCY_ENTITY_TO_LIST_ITEM
import com.marazmone.crypton.di.MapperNamed.CURRENCY_RESPONSE_TO_ENTITY
import com.marazmone.crypton.domain.repository.CurrencyRepository
import org.koin.core.module.dsl.named
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    factory<CurrencyRepository> {
        CurrencyRepositoryImpl(
            remote = get(),
            cache = get(),
            responseToEntityMapper = get(qualifier = named(CURRENCY_RESPONSE_TO_ENTITY)),
            entityToListItemMapper = get(qualifier = named(CURRENCY_ENTITY_TO_LIST_ITEM)),
            entityToDetailMapper = get(qualifier = named(CURRENCY_ENTITY_TO_DETAIL)),
        )
    }
}
