package com.marazmone.crypton.di

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.data.mapper.base.Mapper
import com.marazmone.crypton.data.mapper.currency.CurrencyResponseToEntity
import com.marazmone.crypton.data.remote.response.CurrencyResponse
import com.marazmone.crypton.domain.mapper.currency.CurrencyEntityToListItemMapper
import com.marazmone.crypton.domain.model.CurrencyListItem
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.named
import org.koin.dsl.module

val mapperModule = module {
    factoryOf(::CurrencyResponseToEntity) {
        named("1")
        bind<Mapper<CurrencyResponse, CurrencyEntity>>()
    }
    factoryOf(::CurrencyEntityToListItemMapper) {
        named("2")
        bind<Mapper<CurrencyEntity, CurrencyListItem>>()
    }
}