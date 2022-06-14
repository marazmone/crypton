package com.marazmone.crypton.domain.model

import com.marazmone.crypton.utils.amountWithCurrency
import com.marazmone.crypton.utils.format.KDecimalFormat

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
}