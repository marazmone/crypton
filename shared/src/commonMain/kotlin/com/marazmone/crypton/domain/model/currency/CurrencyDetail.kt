package com.marazmone.crypton.domain.model.currency

import com.marazmone.crypton.utils.amountWithCurrency
import com.marazmone.crypton.utils.coolNumber

data class CurrencyDetail(
    val id: String,
    val name: String,
    var isFavorite: Boolean = false,
    val percentChange1H: Float,
    val percentChange24H: Float,
    val percentChange7D: Float,
    private val _price: Float,
    val symbol: String,
    val rank: Int,
    val imageUrl: String,
    private val _marketCap: Float,
    private val _supplyCirculating: Float,
    private val _supplyTotal: Float,
    private val _supplyMax: Float,
    val tradingVolume: Float,
    val max24H: Float,
    val min24H: Float,
    val athDate: Long,
    val athPrice: Float,
    val athPercentChange: Float,
    val atlDate: Long,
    val atlPrice: Float,
    val atlPercentChange: Float,
) {

    val price: String
        get() = _price.amountWithCurrency()
    val marketCap: String
        get() = _marketCap.amountWithCurrency()
    val supplyCirculating: String
        get() = _supplyCirculating.coolNumber
    val supplyTotal: String
        get() = if (_supplyTotal == 0f) "∞" else _supplyTotal.coolNumber
    val supplyMax: String
        get() = if (_supplyMax == 0f) "∞" else _supplyMax.coolNumber
    val supplyProgress: Int
        get() = (_supplyCirculating / (if (_supplyMax == 0f) _supplyTotal else _supplyMax) * 100).toInt()

    companion object {

        val template: CurrencyDetail
            get() = CurrencyDetail(
                "",
                "Empty Token",
                false,
                4f,
                1.4f,
                -5.4f,
                223.44f,
                "SOL",
                5,
                "",
                123321123.1233212f,
                12332f,
                123321f,
                1233211f,
                max24H = 46000f,
                min24H = 40000f,
                tradingVolume = 149009900f,
                athDate = 1638776303,
                athPrice = 69000f,
                athPercentChange = -35f,
                atlPrice = 35f,
                atlPercentChange = 10000f,
                atlDate = 1386315503,
            )
    }
}
