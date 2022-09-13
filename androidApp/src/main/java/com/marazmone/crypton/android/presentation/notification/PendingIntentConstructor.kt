package com.marazmone.crypton.android.presentation.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.marazmone.crypton.android.BuildConfig
import com.marazmone.crypton.android.presentation.screen.MainActivity
import com.marazmone.crypton.android.presentation.util.CommonUtils.pendingIntentFlag

object PendingIntentConstructor {

    private const val NOTIFICATION_TYPE = "pending_intent_constructor_notification_type"
    private const val NOTIFICATION_DATA = "pending_intent_constructor_notification_data"

    fun buildPendingIntent(
        context: Context,
        notificationType: NotificationType,
        notificationData: NotificationData? = null
    ): PendingIntent? {
        val newIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            action = BuildConfig.APPLICATION_ID.plus(Math.random())
            putExtra(NOTIFICATION_TYPE, notificationType.type)
        }
        if (notificationData != null) newIntent.putExtra(
            NOTIFICATION_DATA,
            notificationData.toBundle()
        )
        return PendingIntent.getActivity(context, 0, newIntent, pendingIntentFlag)
    }
}