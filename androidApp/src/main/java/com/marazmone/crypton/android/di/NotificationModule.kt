package com.marazmone.crypton.android.di

import com.marazmone.crypton.android.presentation.notification.NotificationCreator
import com.marazmone.crypton.android.presentation.notification.NotificationCreatorFactory
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val notificationModule = module {
    singleOf(::NotificationCreatorFactory) { bind<NotificationCreator>()}
}