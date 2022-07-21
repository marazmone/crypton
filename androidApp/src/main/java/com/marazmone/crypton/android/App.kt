package com.marazmone.crypton.android

import android.app.Application
import com.marazmone.crypton.android.di.notificationModule
import com.marazmone.crypton.android.di.viewModelModule
import com.marazmone.crypton.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(
                viewModelModule,
                notificationModule,
            )
        }
    }
}