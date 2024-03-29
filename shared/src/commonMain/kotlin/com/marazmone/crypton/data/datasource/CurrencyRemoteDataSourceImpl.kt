package com.marazmone.crypton.data.datasource

import com.marazmone.crypton.data.remote.RemoteConst.QueryParams.IDS
import com.marazmone.crypton.data.remote.RemoteConst.QueryParams.ORDER
import com.marazmone.crypton.data.remote.RemoteConst.QueryParams.PAGE
import com.marazmone.crypton.data.remote.RemoteConst.QueryParams.PER_PAGE
import com.marazmone.crypton.data.remote.RemoteConst.QueryParams.PRICE_CHANGE_PERCENTAGE
import com.marazmone.crypton.data.remote.RemoteConst.QueryParams.VS_CURRENCY
import com.marazmone.crypton.data.remote.RemoteConst.Url.GET_ALL
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
    ): List<CurrencyResponse> {
        val request = api.get(GET_ALL) {
            url {
                parameters.append(VS_CURRENCY, vsCurrency)
                parameters.append(PAGE, "1")
                parameters.append(PER_PAGE, "100")
                parameters.append(ORDER, "market_cap_desc")
                parameters.append(PRICE_CHANGE_PERCENTAGE, priceChangePercentage)
                ids?.also { parameters.append(IDS, it) }
            }
        }
        return request.body()
    }
}
