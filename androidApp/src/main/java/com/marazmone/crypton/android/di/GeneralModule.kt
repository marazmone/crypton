package com.marazmone.crypton.android.di

import androidx.work.WorkManager
import org.koin.dsl.module

val generalModule = module {
    single {
        WorkManager.getInstance(get())
    }
}
