package com.marazmone.crypton.android.presentation.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.marazmone.crypton.android.presentation.usecase.DailyRateCheckWorkerStartUseCase
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AlarmReceiver : BroadcastReceiver(), KoinComponent, CoroutineScope {

    private val dailyRateCheckWorkerStartUseCase by inject<DailyRateCheckWorkerStartUseCase>()

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.orEmpty() == ReceiverName.DAILY_REMINDER) {
            launch {
                dailyRateCheckWorkerStartUseCase.execute()
            }
        }
    }
}