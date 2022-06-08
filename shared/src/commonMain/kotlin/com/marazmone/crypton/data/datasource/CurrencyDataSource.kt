package com.marazmone.crypton.data.datasource

import com.marazmone.crypton.data.remote.response.CurrencyResponse

interface CurrencyCacheDataSource

interface CurrencyRemoteDataSource {

    suspend fun getAll(
        vsCurrency: String = "USD",
        priceChangePercentage: String = "1h,7d",
        ids: String? = null,
    ): List<CurrencyResponse>
}