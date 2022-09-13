package com.marazmone.crypton.android.di

import com.marazmone.crypton.android.presentation.worker.ComparisonWorker
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val workerManagerModule = module {
    worker { ComparisonWorker(get(), get(), get(), get(), get()) }
}