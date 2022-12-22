package com.marazmone.crypton.android.presentation.ui.component.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marazmone.crypton.android.R.string
import com.marazmone.crypton.android.presentation.ui.AppTheme

@Composable
fun PercentChangeWithPeriodWidget(
    @StringRes period: Int,
    percentChange: Float,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(id = period), style = MaterialTheme.typography.labelMedium)
        PercentChangeWidget(
            percentChange = percentChange,
            verticalAlignment = Alignment.CenterVertically,
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun PercentChangeWithPeriodWidget_UP_Preview() {
    AppTheme {
        PercentChangeWithPeriodWidget(
            period = string.percent_change_1h,
            percentChange = 10.33f
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun PercentChangeWithPeriodWidget_DOWN_Preview() {
    AppTheme {
        PercentChangeWithPeriodWidget(
            period = string.percent_change_1d,
            percentChange = -10.33f
        )
    }
}
