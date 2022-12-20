package com.marazmone.crypton.utils

import com.marazmone.crypton.utils.Currency.Fiat.USD
import com.marazmone.crypton.utils.format.KDecimalFormat

val Float?.orZero
    @Suppress("MagicNumber")
    get() = this ?: 0f

val Long?.orZero
    @Suppress("MagicNumber")
    get() = this ?: 0L

val Int?.orZero
    @Suppress("MagicNumber")
    get() = this ?: 0

val Long.secToMs
    @Suppress("MagicNumber")
    get() = this * 1000

val Long.msToSec
    @Suppress("MagicNumber")
    get() = this / 1000

val Float.coolNumber: String get() = KDecimalFormat.coolNumber(this)

fun Float.amountWithCurrency(currency: String = USD): String = coolNumber.plus(currency)
