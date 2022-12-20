package com.marazmone.crypton.android.presentation.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.marazmone.crypton.android.presentation.usecase.DailyRateCheckAlarmStartUseCase
import com.marazmone.crypton.domain.usecase.reminder.DailyReminderStartedSaveUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class RebootReceiver : BroadcastReceiver(), KoinComponent, CoroutineScope {

    private val dailyReminderStartedSaveUseCase by inject<DailyReminderStartedSaveUseCase>()

    private val dailyRateCheckAlarmStartUseCase by inject<DailyRateCheckAlarmStartUseCase>()

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        launch {
            dailyReminderStartedSaveUseCase.execute(false)
            dailyRateCheckAlarmStartUseCase.execute()
        }
    }
}
