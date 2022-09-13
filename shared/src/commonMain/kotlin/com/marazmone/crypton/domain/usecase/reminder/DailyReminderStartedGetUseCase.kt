package com.marazmone.crypton.domain.usecase.reminder

import com.marazmone.crypton.data.cache.LocalCacheManager

class DailyReminderStartedGetUseCase(
    private val localCacheManager: LocalCacheManager,
) {

    fun execute(): Boolean = localCacheManager.dailyReminderStarted
}