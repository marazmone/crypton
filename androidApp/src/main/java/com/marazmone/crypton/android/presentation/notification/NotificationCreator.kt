package com.marazmone.crypton.android.presentation.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

abstract class NotificationCreator(private val context: Context) {

    private val notificationManager by lazy { NotificationManagerCompat.from(context) }

    protected abstract fun recognizeNotification(pushMessage: PushMessage): BaseNotification

    fun showNotification(pushMessage: PushMessage) {
        val notification = recognizeNotification(pushMessage)
        showNotificationInternal(notification)
    }

    private fun showNotificationInternal(notification: BaseNotification) {
        createNotificationChannel(notification)

        val notificationPriority =
            getNotificationPriority(notification.notificationChannelDescription.importance)
        val largeIcon = BitmapFactory.decodeResource(context.resources, notification.getLargeIcon())
        val builder = NotificationCompat.Builder(
            context,
            notification.notificationChannelDescription.channelId
        )
            .setSmallIcon(notification.getSmallIcon())
            .setLargeIcon(largeIcon)
            .setStyle(NotificationCompat.BigTextStyle().bigText(notification.getContent()))
            .setContentTitle(notification.getTitle())
            .setContentText(notification.getContent())
            .setContentIntent(notification.configurePendingIntent(context))
            .setPriority(notificationPriority)
            .setAutoCancel(true)

        notificationManager.notify(notification.hashCode(), builder.build())
    }

    private fun createNotificationChannel(notification: BaseNotification) {
        val channelDescription = notification.notificationChannelDescription
        val name = context.getString(channelDescription.channelName)
        val descriptionText = context.getString(channelDescription.description)
        val importance = getNotificationImportance(channelDescription.importance)
        val channel = NotificationChannel(channelDescription.channelId, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("InlinedApi")
    private fun getNotificationImportance(importance: NotificationImportance): Int {
        return when (importance) {
            NotificationImportance.IMPORTANCE_NONE -> NotificationManager.IMPORTANCE_NONE
            NotificationImportance.IMPORTANCE_MIN -> NotificationManager.IMPORTANCE_MIN
            NotificationImportance.IMPORTANCE_LOW -> NotificationManager.IMPORTANCE_LOW
            NotificationImportance.IMPORTANCE_DEFAULT -> NotificationManager.IMPORTANCE_DEFAULT
            NotificationImportance.IMPORTANCE_HIGH -> NotificationManager.IMPORTANCE_HIGH
            NotificationImportance.IMPORTANCE_MAX -> {
                // don't set the NotificationManager.IMPORTANCE_MAX. Thi is unused in the Android SDK
                NotificationManager.IMPORTANCE_HIGH
            }
        }
    }

    private fun getNotificationPriority(importance: NotificationImportance): Int {
        return when (importance) {
            NotificationImportance.IMPORTANCE_NONE -> NotificationCompat.PRIORITY_LOW
            NotificationImportance.IMPORTANCE_MIN -> NotificationCompat.PRIORITY_MIN
            NotificationImportance.IMPORTANCE_LOW -> NotificationCompat.PRIORITY_LOW
            NotificationImportance.IMPORTANCE_DEFAULT -> NotificationCompat.PRIORITY_DEFAULT
            NotificationImportance.IMPORTANCE_HIGH -> NotificationCompat.PRIORITY_HIGH
            NotificationImportance.IMPORTANCE_MAX -> NotificationCompat.PRIORITY_MAX
        }
    }
}