package com.marazmone.crypton.android.presentation.usecase

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.marazmone.crypton.android.presentation.util.getWorkInfosByTagSuspended
import com.marazmone.crypton.android.presentation.worker.ComparisonWorker
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit

private const val CHECK_RATE_WORK_TAG = "check_rate_work_tag_new"

class DailyRateCheckStartUseCase(
    private val workManager: WorkManager,
) {

    suspend fun execute() {
        val works: List<WorkInfo> = workManager.getWorkInfosByTagSuspended(CHECK_RATE_WORK_TAG)
        if (works.isNotEmpty() && (works[0].state == WorkInfo.State.ENQUEUED || works[0].state == WorkInfo.State.RUNNING)) return
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val secondsDelay = getInitialSecondsDelayInterval()
        val startRateWork = PeriodicWorkRequestBuilder<ComparisonWorker>(24, TimeUnit.HOURS)
            .setConstraints(constraints)
            .setInitialDelay(secondsDelay, TimeUnit.MILLISECONDS)
            .setInitialDelay(15, TimeUnit.MINUTES)
            .addTag(CHECK_RATE_WORK_TAG)
            .build()
        workManager.enqueue(startRateWork)
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