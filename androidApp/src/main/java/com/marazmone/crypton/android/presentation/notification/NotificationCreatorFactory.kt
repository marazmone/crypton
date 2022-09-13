package com.marazmone.crypton.android.presentation.notification

import android.content.Context
import com.marazmone.crypton.android.presentation.notification.type.DailyCurrencyRateChangesNotification

class NotificationCreatorFactory (
    context: Context
) : NotificationCreator(context) {

    override fun recognizeNotification(pushMessage: PushMessage): BaseNotification {
        return when (pushMessage.getType()) {
            NotificationType.DailyCurrencyRateChanges -> {
                DailyCurrencyRateChangesNotification(pushMessage)
            }
        }
    }
}
