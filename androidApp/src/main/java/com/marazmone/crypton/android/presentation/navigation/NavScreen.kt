package com.marazmone.crypton.android.presentation.navigation

sealed class NavScreen(val route: String) {

    object CurrencyDetail : NavScreen("currency_detail/{${Arguments.CURRENCY_DETAIL_ID}}") {

        fun createRoute(currencyId: String) = "currency_detail/$currencyId"
    }
}

object Arguments {

    const val CURRENCY_DETAIL_ID = "currency_detail_id"
}