package com.marazmone.crypton.android.presentation.notification

import java.util.*


sealed interface PushMessage {
    fun getType(): NotificationType
    fun getData(): Map<String, String>
}

/**
 * Body - List<CurrencyChange> converted to String with Json.encodeToString(...)
 */
data class DailyCurrencyRateChangesMessage(
    private val id: String = UUID.randomUUID().toString(),
    private val title: String,
    private val body: String,
) : PushMessage {

    override fun getType() = NotificationType.DailyCurrencyRateChanges

    override fun getData(): Map<String, String> {
        return mapOf(
            NotificationKeys.KEY_TITLE to title,
            NotificationKeys.KEY_ID to id,
            NotificationKeys.KEY_BODY to body,
        )
    }

}