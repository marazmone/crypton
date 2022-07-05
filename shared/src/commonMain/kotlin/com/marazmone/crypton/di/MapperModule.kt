package com.marazmone.crypton.di

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.data.mapper.base.Mapper
import com.marazmone.crypton.data.mapper.currency.CurrencyResponseToEntity
import com.marazmone.crypton.data.remote.response.CurrencyResponse
import com.marazmone.crypton.di.MapperNamed.CURRENCY_ENTITY_TO_DETAIL
import com.marazmone.crypton.di.MapperNamed.CURRENCY_ENTITY_TO_LIST_ITEM
import com.marazmone.crypton.di.MapperNamed.CURRENCY_RESPONSE_TO_ENTITY
import com.marazmone.crypton.domain.mapper.currency.CurrencyEntityToDetailMapper
import com.marazmone.crypton.domain.mapper.currency.CurrencyEntityToListItemMapper
import com.marazmone.crypton.domain.model.currency.CurrencyDetail
import com.marazmone.crypton.domain.model.currency.CurrencyListItem
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.named
import org.koin.dsl.module

val currencyMapperModule = module {
    factoryOf(::CurrencyResponseToEntity) {
        named(CURRENCY_RESPONSE_TO_ENTITY)
        bind<Mapper<CurrencyResponse, CurrencyEntity>>()
    }
    factoryOf(::CurrencyEntityToListItemMapper) {
        named(CURRENCY_ENTITY_TO_LIST_ITEM)
        bind<Mapper<CurrencyEntity, CurrencyListItem>>()
    }
    factoryOf(::CurrencyEntityToDetailMapper) {
        named(CURRENCY_ENTITY_TO_DETAIL)
        bind<Mapper<CurrencyEntity, CurrencyDetail>>()
    }
}

object MapperNamed {

    const val CURRENCY_RESPONSE_TO_ENTITY = "currency_response_to_entity"
    const val CURRENCY_ENTITY_TO_LIST_ITEM = "currency_entity_to_list_item"
    const val CURRENCY_ENTITY_TO_DETAIL = "currency_entity_to_detail"
}