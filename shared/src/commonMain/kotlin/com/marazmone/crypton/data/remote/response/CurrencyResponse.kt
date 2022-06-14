package com.marazmone.crypton.data.remote.response

import com.marazmone.crypton.data.mapper.base.Mapper
import com.marazmone.crypton.domain.model.CurrencyListItem
import com.marazmone.crypton.utils.orZero
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("symbol")
    val symbol: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("current_price")
    val currentPrice: Float? = null,
    @SerialName("market_cap")
    val marketCap: Float? = null,
    @SerialName("market_cap_rank")
    val marketCapRank: Int? = null,
    @SerialName("total_volume")
    val totalValue: Float? = null,
    @SerialName("high_24h")
    val high24h: Float? = null,
    @SerialName("low_24h")
    val low24h: Float? = null,
    @SerialName("price_change_24h")
    val priceChange24h: Float? = null,
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24h: Float? = null,
    @SerialName("market_cap_change_24h")
    val marketCapChange24h: Float? = null,
    @SerialName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Float? = null,
    @SerialName("circulating_supply")
    val circulatingSupply: Float? = null,
    @SerialName("total_supply")
    val totalSupply: Float? = null,
    @SerialName("max_supply")
    val maxSupply: Float? = null,
    @SerialName("ath")
    val ath: Float? = null,
    @SerialName("ath_change_percentage")
    val athChangePercentage: Float? = null,
    @SerialName("ath_date")
    val athDate: String? = null,
    @SerialName("atl")
    val atl: Float? = null,
    @SerialName("atl_change_percentage")
    val atlChangePercentage: Float? = null,
    @SerialName("atl_date")
    val atlDate: String? = null,
    @SerialName("last_updated")
    val lastUpdated: String? = null,
    @SerialName("price_change_percentage_1h_in_currency")
    val priceChangePercentage1hInCurrency: Float? = null,
    @SerialName("price_change_percentage_7d_in_currency")
    val priceChangePercentage7dInCurrency: Float? = null,
) {

    companion object : Mapper<CurrencyResponse, CurrencyListItem> {

        override fun map(source: CurrencyResponse): CurrencyListItem =
            source.run {
                CurrencyListItem(
                    id = id.orEmpty(),
                    rank = marketCapRank.orZero,
                    symbol = symbol.orEmpty().uppercase(),
                    name = name.orEmpty(),
                    percentChange24H = priceChangePercentage24h.orZero,
                    _price = currentPrice.orZero,
                    _mCap = marketCap.orZero,
                    imageUrl = image.orEmpty(),
                    isFavorite = false
                )
            }
    }
}