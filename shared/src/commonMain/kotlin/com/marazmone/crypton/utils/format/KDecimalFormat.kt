package com.marazmone.crypton.utils.format

expect object KDecimalFormat {

    fun coolNumber(number: Float): String

    fun formatToString(number: Float): String
}