package com.marazmone.crypton.di

import com.marazmone.crypton.data.cache.LocalCacheManager
import com.marazmone.crypton.data.cache.LocalCacheManagerImpl
import com.marazmone.crypton.utils.prefs.KMMPreference
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val utilModule: Module