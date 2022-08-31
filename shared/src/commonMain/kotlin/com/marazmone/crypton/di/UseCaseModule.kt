package com.marazmone.crypton.di

import com.marazmone.crypton.domain.usecase.currency.CurrencyGetAllFavoriteUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyGetAllUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyGetByIdUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyObserveAllFavoriteUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyObserveAllUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencySetFavoriteByIdUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyUpdateByIdsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val currencyUseCaseModule = module {
    factoryOf(::CurrencyGetAllUseCase)
    factoryOf(::CurrencyObserveAllUseCase)
    factoryOf(::CurrencyGetByIdUseCase)
    factoryOf(::CurrencySetFavoriteByIdUseCase)
    factoryOf(::CurrencyObserveAllFavoriteUseCase)
    factoryOf(::CurrencyUpdateByIdsUseCase)
    factoryOf(::CurrencyGetAllFavoriteUseCase)
}