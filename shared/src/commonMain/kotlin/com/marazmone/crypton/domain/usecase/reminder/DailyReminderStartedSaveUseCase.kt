package com.marazmone.crypton.domain.usecase.reminder

import com.marazmone.crypton.data.cache.LocalCacheManager
import kotlinx.coroutines.withContext

class DailyReminderStartedSaveUseCase(
    private val localCacheManager: LocalCacheManager,
) {

    fun execute() {
        localCacheManager.dailyReminderStarted = true
    }
}