package com.marazmone.crypton.utils

import com.marazmone.crypton.utils.Currency.Fiat.USD
import com.marazmone.crypton.utils.format.KDecimalFormat

val Float?.orZero get() = this ?: 0f

val Long?.orZero get() = this ?: 0L

val Int?.orZero get() = this ?: 0

val Long.secToMs get() = this * 1000

val Long.msToSec get() = this / 1000

fun Float.amountWithCurrency(currency: String = USD): String =
    KDecimalFormat.coolNumber(this).plus(currency)