package com.marazmone.crypton.android.presentation.util

import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.await

suspend fun WorkManager.getWorkInfosByTagSuspended(tag: String): List<WorkInfo> {
    return getWorkInfosByTag(tag).await()
}