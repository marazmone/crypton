package com.marazmone.crypton.data.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.marazmone.crypton.utils.orFalse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LocalCacheManagerImpl(
    private val dataStore: DataStore<Preferences>,
) : LocalCacheManager {

    private val scope = CoroutineScope(Dispatchers.Default)

    private val dailyReminderStarted = booleanPreferencesKey("daily_reminder_started")

    override fun getDailyReminderStarted(): Flow<Boolean> = dataStore.data.map {
        it[dailyReminderStarted].orFalse
    }

    override fun saveDailyReminderStarted(value: Boolean) {
        scope.launch {
            dataStore.edit {
                it[dailyReminderStarted] = value
            }
        }
    }
}
