package com.marazmone.crypton.android.presentation.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openUrlFromBrowser(url: String, actionFallback: ((url: String) -> Unit)? = null) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    } catch (e: Throwable) {
        e.printStackTrace()
        actionFallback?.invoke(url)
    }
}
