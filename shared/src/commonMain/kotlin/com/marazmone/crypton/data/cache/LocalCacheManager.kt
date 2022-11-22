package com.marazmone.crypton.data.cache

import kotlinx.coroutines.flow.Flow

interface LocalCacheManager {

    fun getDailyReminderStarted(): Flow<Boolean>

    fun saveDailyReminderStarted(value: Boolean)
}