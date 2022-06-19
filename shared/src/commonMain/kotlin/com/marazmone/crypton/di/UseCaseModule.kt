package com.marazmone.crypton.di

import com.marazmone.crypton.domain.usecase.CurrencyGetAllUseCase
import com.marazmone.crypton.domain.usecase.CurrencyObserveAllUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::CurrencyGetAllUseCase)
    factoryOf(::CurrencyObserveAllUseCase)
}