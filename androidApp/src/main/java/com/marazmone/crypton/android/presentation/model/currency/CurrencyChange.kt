package com.marazmone.crypton.android.presentation.model.currency

import com.marazmone.crypton.utils.amountWithCurrency
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyChange(
    val symbol: String,
    val percentChange24H: Float,
    val isGrewUp: Boolean
) {

    val percentChange24HFormatted: String get() = percentChange24H.amountWithCurrency("%")
}