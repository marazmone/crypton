package com.marazmone.crypton.domain.usecase.reminder

import com.marazmone.crypton.data.cache.LocalCacheManager
import kotlinx.coroutines.flow.first

class DailyReminderStartedGetUseCase(
    private val localCacheManager: LocalCacheManager,
) {

    suspend fun execute(): Boolean = localCacheManager
        .getDailyReminderStarted()
        .first()
}
