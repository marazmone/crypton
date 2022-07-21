package com.marazmone.crypton.android.presentation.notification

import android.os.Bundle

sealed interface  NotificationData {
    fun toBundle(): Bundle
}