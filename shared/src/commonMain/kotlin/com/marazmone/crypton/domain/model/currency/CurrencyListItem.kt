package com.marazmone.crypton.domain.model.currency

import com.marazmone.crypton.utils.amountWithCurrency
import com.marazmone.crypton.utils.format.KDecimalFormat
import kotlin.random.Random

data class CurrencyListItem(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val percentChange24H: Float,
    val isFavorite: Boolean,
    val imageUrl: String,
    private val _price: Float,
    private val _mCap: Float,
) {

    val title: String
        get() = "#"
            .plus(rank)
            .plus(" ")
            .plus(symbol)

    val price: String get() = _price.amountWithCurrency()

    fun mCap(mCapText: String) = mCapText
        .plus(" ")
        .plus(KDecimalFormat.coolNumber(_mCap))
        .plus(" $")

    companion object {

        private fun emptyItem(rank: Int): CurrencyListItem = CurrencyListItem(
                id = Random.nextInt().toString(),
                name = "Currency name",
                symbol = "SML",
                rank = rank,
                percentChange24H = Random.nextDouble(-90.0, 90.0).toFloat(),
                _price = Random.nextDouble(1.0, 50_000.0).toFloat(),
                _mCap = Random.nextDouble(100_000_000.0, 10_000_000_000.0).toFloat(),
                isFavorite = Random.nextBoolean(),
                imageUrl = "",
            )

        fun emptyList(): List<CurrencyListItem> = List(100) {
            emptyItem(it)
        }
    }
}