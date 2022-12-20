package com.marazmone.crypton.android.presentation.notification

sealed class NotificationType(val type: String) {

    object DailyCurrencyRateChanges : NotificationType("daily_currency_changes")
}
