package com.marazmone.crypton.android.presentation.notification

import androidx.annotation.StringRes
import com.marazmone.crypton.android.R

enum class NotificationImportance {
    IMPORTANCE_NONE,
    IMPORTANCE_MIN,
    IMPORTANCE_LOW,
    IMPORTANCE_DEFAULT,
    IMPORTANCE_HIGH,
    IMPORTANCE_MAX,
}

const val CurrencyRateChangesId = "currency_rate_changes_id"

sealed class NotificationChannelDescription(
    val channelId: String,
    @StringRes val channelName: Int,
    @StringRes val description: Int,
    val importance: NotificationImportance = NotificationImportance.IMPORTANCE_MAX
) {

    object DailyCurrencyRateChanges : NotificationChannelDescription(
        channelId = CurrencyRateChangesId,
        channelName = R.string.notification_channel_currency_changes,
        description = R.string.notification_channel_daily_currency_changes
    )
}
