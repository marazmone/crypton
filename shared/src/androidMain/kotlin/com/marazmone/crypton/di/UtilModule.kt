package com.marazmone.crypton.di

import com.marazmone.crypton.data.cache.LocalCacheManager
import com.marazmone.crypton.data.cache.LocalCacheManagerImpl
import com.marazmone.crypton.data.cache.createDataStoreAndroid
import org.koin.core.module.Module
import org.koin.dsl.module

actual val utilModule: Module = module {
    single<LocalCacheManager> {
        LocalCacheManagerImpl(
            createDataStoreAndroid(get())
        )
    }
}