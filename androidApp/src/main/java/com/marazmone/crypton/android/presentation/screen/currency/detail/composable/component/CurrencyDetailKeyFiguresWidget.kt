package com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.R.string
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Colors.Gradient.LightEnd
import com.marazmone.crypton.android.presentation.ui.Colors.Gradient.LightStart
import com.marazmone.crypton.android.presentation.ui.component.common.ProgressBarWidget
import com.marazmone.crypton.domain.model.currency.CurrencyDetail

@Composable
fun CurrencyDetailKeyFiguresWidget(
    model: CurrencyDetail,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.key_figures),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            KeyFiguresItem(
                topText = stringResource(id = string.symbol),
                bottomText = model.symbol
            )
            KeyFiguresItem(
                topText = stringResource(id = string.rank),
                bottomText = model.rank.toString()
            )
            KeyFiguresItem(
                topText = stringResource(id = string.market_cap_full),
                bottomText = model.marketCap
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.supply),
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            KeyFiguresItem(
                topText = stringResource(id = string.supply_circulating),
                bottomText = model.supplyCirculating
            )
            KeyFiguresItem(
                topText = stringResource(id = string.supply_total),
                bottomText = model.supplyTotal
            )
            KeyFiguresItem(
                topText = stringResource(id = string.supply_max),
                bottomText = model.supplyMax
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        ProgressBarWidget(
            width = screenWidth,
            backgroundColor = Color.White,
            foregroundColor = Brush.horizontalGradient(listOf(LightStart, LightEnd)),
            percent = model.supplyProgress,
            modifier = Modifier
                .clip(shape = CircleShape)
                .height(12.dp),
        )
    }
}

@Composable
private fun KeyFiguresItem(
    topText: String,
    bottomText: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = topText.uppercase(),
            style = MaterialTheme.typography.labelMedium.copy(Color.White.copy(alpha = 0.6f))
        )
        Text(
            text = bottomText,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun KeyFiguresItem_Preview() {
    AppTheme {
        KeyFiguresItem(
            topText = "SYMBOL",
            bottomText = "SOL",
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun CurrencyDetailKeyFiguresComponent_Preview() {
    AppTheme {
        CurrencyDetailKeyFiguresWidget(
            model = CurrencyDetail.template,
        )
    }
}
