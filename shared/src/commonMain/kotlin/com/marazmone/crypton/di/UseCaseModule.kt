package com.marazmone.crypton.di

import com.marazmone.crypton.domain.usecase.CurrencyGetAllUseCase
import com.marazmone.crypton.domain.usecase.CurrencyGetByIdUseCase
import com.marazmone.crypton.domain.usecase.CurrencyObserveAllUseCase
import com.marazmone.crypton.domain.usecase.CurrencySetFavoriteByIdUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val currencyUseCaseModule = module {
    factoryOf(::CurrencyGetAllUseCase)
    factoryOf(::CurrencyObserveAllUseCase)
    factoryOf(::CurrencyGetByIdUseCase)
    factoryOf(::CurrencySetFavoriteByIdUseCase)
}