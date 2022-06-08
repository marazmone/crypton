package com.marazmone.crypton.data.datasource

import com.marazmone.crypton.data.remote.RemoteConst
import com.marazmone.crypton.data.remote.RemoteConst.QueryParams.IDS
import com.marazmone.crypton.data.remote.RemoteConst.QueryParams.PRICE_CHANGE_PERCENTAGE
import com.marazmone.crypton.data.remote.RemoteConst.QueryParams.VS_CURRENCY
import com.marazmone.crypton.data.remote.RemoteConst.Url.BASE
import com.marazmone.crypton.data.remote.response.CurrencyResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class CurrencyRemoteDataSourceImpl(
    private val api: HttpClient
) : CurrencyRemoteDataSource {

    override suspend fun getAll(
        vsCurrency: String,
        priceChangePercentage: String,
        ids: String?
    ): List<CurrencyResponse> =
        api
            .get(BASE) {
                url {
                    parameter(VS_CURRENCY, vsCurrency)
                    parameter(PRICE_CHANGE_PERCENTAGE, priceChangePercentage)
                    ids?.also { parameter(IDS, it) }
                }
            }
            .body()
}