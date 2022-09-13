package com.marazmone.crypton.android.di

import com.marazmone.crypton.android.presentation.usecase.DailyRateCheckStartUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::DailyRateCheckStartUseCase)
}