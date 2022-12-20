package com.marazmone.crypton.android.presentation.ui.component.currency

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.ui.component.common.PercentChangeWithPeriodComponent
import com.marazmone.crypton.domain.model.currency.CurrencyDetail

@Composable
fun CurrencyDetailPriceComponent(model: CurrencyDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = model.price,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PercentChangeWithPeriodComponent(
                period = R.string.percent_change_1h,
                percentChange = model.percentChange1H
            )
            PercentChangeWithPeriodComponent(
                period = R.string.percent_change_1d,
                percentChange = model.percentChange24H
            )
            PercentChangeWithPeriodComponent(
                period = R.string.percent_change_1w,
                percentChange = model.percentChange7D
            )
        }
    }
}
