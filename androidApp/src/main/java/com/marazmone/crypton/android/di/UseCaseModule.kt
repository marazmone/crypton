package com.marazmone.crypton.android.di

import com.marazmone.crypton.android.presentation.usecase.DailyRateCheckAlarmStartUseCase
import com.marazmone.crypton.android.presentation.usecase.DailyRateCheckWorkerStartUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::DailyRateCheckWorkerStartUseCase)
    factoryOf(::DailyRateCheckAlarmStartUseCase)
}
