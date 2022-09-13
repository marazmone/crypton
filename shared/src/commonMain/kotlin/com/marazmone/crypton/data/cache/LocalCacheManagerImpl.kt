package com.marazmone.crypton.data.cache

import com.marazmone.crypton.utils.prefs.KMMPreference

private const val DAILY_REMINDER_STARTED = "daily_reminder_started"

class LocalCacheManagerImpl(
    private val prefs: KMMPreference,
) : LocalCacheManager {

    override var dailyReminderStarted: Boolean
        get() = prefs.getBool(DAILY_REMINDER_STARTED)
        set(value) = prefs.put(DAILY_REMINDER_STARTED, value)
}