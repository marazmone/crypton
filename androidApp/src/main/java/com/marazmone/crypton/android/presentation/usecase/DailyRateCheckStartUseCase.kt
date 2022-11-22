package com.marazmone.crypton.android.presentation.usecase

import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.marazmone.crypton.android.presentation.worker.ComparisonWorker
import com.marazmone.crypton.domain.usecase.reminder.DailyReminderStartedGetUseCase
import com.marazmone.crypton.domain.usecase.reminder.DailyReminderStartedSaveUseCase
import io.github.aakira.napier.Napier
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit

private const val CHECK_RATE_WORK_TAG = "check_rate_work_tag_new"

class DailyRateCheckStartUseCase(
    private val workManager: WorkManager,
    private val dailyReminderStartedGetUseCase: DailyReminderStartedGetUseCase,
    private val dailyReminderStartedSaveUseCase: DailyReminderStartedSaveUseCase,
) {

    suspend fun execute() {
        if (dailyReminderStartedGetUseCase.execute()) return
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val secondsDelay = getInitialSecondsDelayInterval()
        val startRateWork = PeriodicWorkRequestBuilder<ComparisonWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .setInitialDelay(secondsDelay, TimeUnit.SECONDS)
            .addTag(CHECK_RATE_WORK_TAG)
            .build()
        workManager.enqueue(startRateWork)
        dailyReminderStartedSaveUseCase.execute()
    }

    /**
     * Find the interval in seconds between
     * current time and the 9:00 AM of the next day
     */
    private fun getInitialSecondsDelayInterval(): Long {
        val todayDate: LocalDateTime = LocalDateTime.now()
        val currentTime = todayDate.toEpochSecond(ZoneOffset.UTC)
        val nextDayDate: LocalDateTime = todayDate.plusDays(1)
            .withHour(9)
            .withMinute(0)
            .withSecond(0)
        val nextDateTime = nextDayDate.toEpochSecond(ZoneOffset.UTC)

        return nextDateTime - currentTime
    }
}