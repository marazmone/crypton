package com.marazmone.crypton.android.presentation.notification

import android.app.PendingIntent
import android.content.Context
import androidx.annotation.DrawableRes
import com.marazmone.crypton.android.R

abstract class BaseNotification(protected val pushMessage: PushMessage) {

    abstract val notificationChannelDescription: NotificationChannelDescription

    abstract fun configurePendingIntent(context: Context): PendingIntent?

    open fun getTitle(): String {
        return pushMessage.getData().getOrDefault(NotificationKeys.KEY_TITLE, "")
    }

    open fun getContent(): String {
        return pushMessage.getData().getOrDefault(NotificationKeys.KEY_BODY, "")
    }

    @DrawableRes
    open fun getLargeIcon(): Int = R.mipmap.ic_launcher

    @DrawableRes
    open fun getSmallIcon(): Int = R.mipmap.ic_launcher
}