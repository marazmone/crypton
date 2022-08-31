package com.marazmone.crypton.android.presentation.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.marazmone.crypton.android.presentation.model.currency.CurrencyChange
import com.marazmone.crypton.android.presentation.notification.DailyCurrencyRateChangesMessage
import com.marazmone.crypton.android.presentation.notification.NotificationCreator
import com.marazmone.crypton.domain.usecase.currency.CurrencyGetAllFavoriteUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyUpdateByIdsUseCase
import kotlin.math.absoluteValue
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ComparisonWorker(
    appContext: Context,
    params: WorkerParameters,
    private val currencyGetAllFavoriteUseCase: CurrencyGetAllFavoriteUseCase,
    private val currencyUpdateByIdsUseCase: CurrencyUpdateByIdsUseCase,
    private val notificationCreator: NotificationCreator,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val result = runCatching {
            val favoriteIds = currencyGetAllFavoriteUseCase.execute().map { it.id }
            currencyUpdateByIdsUseCase.execute(favoriteIds)
        }
        if (result.isFailure) return Result.failure()

        val list = result.getOrDefault(emptyList())

        val onlyFavoriteCurrencies = list.filter { it.isFavorite }
            .filter { it.percentChange24H.absoluteValue >= 1.0 }
            .map {
                CurrencyChange(
                    symbol = it.symbol,
                    percentChange24H = it.percentChange24H,
                    isGrewUp = it.percentChange24H > 0
                )
            }

        if (onlyFavoriteCurrencies.isEmpty()) return Result.success()

        val json = Json.encodeToString(onlyFavoriteCurrencies)
        showNotification(json)

        return Result.success()
    }

    private fun showNotification(json: String) {
        notificationCreator.showNotification(
            DailyCurrencyRateChangesMessage(
                title = "Daily currency rate changes",
                body = json,
            )
        )
    }
}