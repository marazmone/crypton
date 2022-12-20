package com.marazmone.crypton.android.presentation.util

import android.app.PendingIntent
import android.os.Build

object CommonUtils {

    val pendingIntentFlag: Int =
        if (isAtLeastSdkVersion(Build.VERSION_CODES.S)) {
            PendingIntent.FLAG_MUTABLE
        } else {
            0
        }

    fun isAtLeastSdkVersion(versionCode: Int): Boolean {
        return Build.VERSION.SDK_INT >= versionCode
    }
}
