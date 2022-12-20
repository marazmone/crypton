package com.marazmone.crypton.android.presentation.notification.type

import android.app.PendingIntent
import android.content.Context
import com.marazmone.crypton.android.presentation.model.currency.CurrencyChange
import com.marazmone.crypton.android.presentation.notification.BaseNotification
import com.marazmone.crypton.android.presentation.notification.NotificationChannelDescription
import com.marazmone.crypton.android.presentation.notification.NotificationKeys
import com.marazmone.crypton.android.presentation.notification.PendingIntentConstructor
import com.marazmone.crypton.android.presentation.notification.PushMessage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DailyCurrencyRateChangesNotification(
    pushMessage: PushMessage
) : BaseNotification(pushMessage) {

    override val notificationChannelDescription: NotificationChannelDescription
        get() = NotificationChannelDescription.DailyCurrencyRateChanges

    override fun getContent(): String {
        val body = pushMessage.getData()[NotificationKeys.KEY_BODY]!!
        val currenciesChanges: List<CurrencyChange> = Json.decodeFromString(body)

        val stringBuilder = buildString {
            currenciesChanges.forEach { item ->
                val currencyText = createCurrencyBody(item)
                append(currencyText)
                append("\n")
            }
        }
        return stringBuilder
    }

    private fun createCurrencyBody(item: CurrencyChange): String {
        val text = if (item.percentChange24H > 0) {
            "\uD83D\uDE80 on" // 🚀
        } else {
            "\uD83C\uDFC2 on" // 🏂
        }
        return item.symbol
            .plus(" ")
            .plus(text)
            .plus(" ")
            .plus(item.percentChange24HFormatted)
    }

    override fun configurePendingIntent(context: Context): PendingIntent? {
        return PendingIntentConstructor.buildPendingIntent(
            context = context,
            notificationType = pushMessage.getType(),
            notificationData = null,
        )
    }
}
