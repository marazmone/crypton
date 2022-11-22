package com.marazmone.crypton.domain.usecase.reminder

import com.marazmone.crypton.data.cache.LocalCacheManager
import kotlin.coroutines.Continuation
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class DailyReminderStartedGetUseCase(
    private val localCacheManager: LocalCacheManager,
) {

    suspend fun execute(): Boolean = localCacheManager
        .getDailyReminderStarted()
        .first()
}