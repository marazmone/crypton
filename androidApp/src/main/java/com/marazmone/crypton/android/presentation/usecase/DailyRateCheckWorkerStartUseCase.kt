package com.marazmone.crypton.android.presentation.usecase

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.marazmone.crypton.android.presentation.worker.ComparisonWorker

private const val CheckRateWorkTag = "check_rate_work_tag_new"

class DailyRateCheckWorkerStartUseCase(
    private val workManager: WorkManager,
) {

    fun execute() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val startRateWork = OneTimeWorkRequestBuilder<ComparisonWorker>()
            .setConstraints(constraints)
            .addTag(CheckRateWorkTag)
            .build()
        workManager.enqueue(startRateWork)
    }
}
