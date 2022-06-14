package com.marazmone.crypton.utils.format

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.ln
import kotlin.math.pow

actual object KDecimalFormat {

    actual fun coolNumber(number: Float): String {
        if (number < 1000000f) return formatToString(number)
        val exp = (ln(number.toDouble()) / ln(1000.0)).toInt()
        val groupingSeparator = ' '
        val decimalSeparator = '.'
        val decimalFormatSymbols = DecimalFormatSymbols.getInstance()
        decimalFormatSymbols.groupingSeparator = groupingSeparator
        decimalFormatSymbols.decimalSeparator = decimalSeparator
        val format = DecimalFormat("0.###")
        format.decimalFormatSymbols = decimalFormatSymbols
        val value: String = format.format(number / 1000.0.pow(exp.toDouble()))
        return String.format(Locale.US, "%s%c", value, "kMBTPE"[exp - 1])
    }

    actual fun formatToString(number: Float): String {
        val groupingSeparator = ' '
        val decimalSeparator = '.'
        val decimalFormatSymbols = DecimalFormatSymbols.getInstance()
        decimalFormatSymbols.groupingSeparator = groupingSeparator
        decimalFormatSymbols.decimalSeparator = decimalSeparator
        val formatter = DecimalFormat()
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2
        formatter.decimalFormatSymbols = decimalFormatSymbols
        return formatter.format(number)
    }
}