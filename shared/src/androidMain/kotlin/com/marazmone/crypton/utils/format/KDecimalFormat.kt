package com.marazmone.crypton.utils.format

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import java.lang.StringBuilder
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
        val formatter = DecimalFormat("###,###.######")
        val stringBuilder = StringBuilder()
        number.toBigDecimal().toString().forEach { char ->
            val charsAfterDot =
                stringBuilder.toString().substringAfter(".", missingDelimiterValue = "")
            if (charsAfterDot.count() >= 2 && charsAfterDot.last() != '0') {
                return@forEach
            }
            stringBuilder.append(char)
        }
        val stringBuilderToDecimal = stringBuilder.toString().toBigDecimal()
        return formatter.format(stringBuilderToDecimal)
    }
}