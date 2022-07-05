package com.marazmone.crypton.android.presentation.model.currency

import android.icu.text.SimpleDateFormat
import androidx.annotation.StringRes
import com.marazmone.crypton.android.R
import com.marazmone.crypton.domain.model.currency.CurrencyDetail
import com.marazmone.crypton.utils.amountWithCurrency
import com.marazmone.crypton.utils.secToMs
import java.util.*

sealed class CurrencyDetailOtherInfo(@StringRes val titleRes: Int) {

    class Simple(titleRes: Int, val text: String) : CurrencyDetailOtherInfo(titleRes)

    /**
     * All time high or All time low
     */
    class AllTime(
        titleRes: Int,
        val percentChange: Float,
        private val _price: Float,
        private val _date: Long,
    ) : CurrencyDetailOtherInfo(titleRes) {

        val price: String get() = _price.amountWithCurrency()

        val date: String
            get() {
                val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                return formatter.format(_date.secToMs)
            }
    }
}

val CurrencyDetail.otherInfo: List<CurrencyDetailOtherInfo>
    get() = listOf(
        CurrencyDetailOtherInfo.Simple(
            titleRes = R.string.detail_other_info_trading_volume,
            text = tradingVolume.amountWithCurrency()
        ),
        CurrencyDetailOtherInfo.Simple(
            titleRes = R.string.detail_other_info_24h_high,
            text = max24H.amountWithCurrency()
        ),
        CurrencyDetailOtherInfo.Simple(
            titleRes = R.string.detail_other_info_24h_low,
            text = min24H.amountWithCurrency()
        ),
        CurrencyDetailOtherInfo.AllTime(
            titleRes = R.string.detail_other_info_ath,
            percentChange = athPercentChange,
            _price = athPrice,
            _date = athDate
        ),
        CurrencyDetailOtherInfo.AllTime(
            titleRes = R.string.detail_other_info_atl,
            percentChange = atlPercentChange,
            _price = atlPrice,
            _date = atlDate
        ),
    )

