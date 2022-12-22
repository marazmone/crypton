package com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.presentation.model.currency.CurrencyDetailOtherInfo
import com.marazmone.crypton.android.presentation.model.currency.otherInfo
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Colors
import com.marazmone.crypton.android.presentation.ui.component.common.PercentChangeWidget
import com.marazmone.crypton.domain.model.currency.CurrencyDetail

@Composable
fun CurrencyDetailOtherInfoWidget(
    model: CurrencyDetail,
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Colors.Main.BackgroundSecond)
            .padding(all = 16.dp)
    ) {
        model.otherInfo.forEachIndexed { index, info ->
            when (info) {
                is CurrencyDetailOtherInfo.Simple -> {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(id = info.titleRes),
                            style = MaterialTheme.typography.bodySmall.copy(Color.White.copy(alpha = 0.6f))
                        )
                        Text(text = info.text, style = MaterialTheme.typography.bodyMedium)
                    }
                }

                is CurrencyDetailOtherInfo.AllTime -> {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column {
                            Text(
                                text = stringResource(id = info.titleRes),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    Color.White.copy(alpha = 0.6f)
                                )
                            )
                            PercentChangeWidget(
                                percentChange = info.percentChange,
                                verticalAlignment = Alignment.CenterVertically,
                                textStyle = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(text = info.price, style = MaterialTheme.typography.bodyMedium)
                            Text(text = info.date, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
            if (index < model.otherInfo.size - 1) {
                Divider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Color.White.copy(alpha = 0.3f),
                    thickness = 0.5.dp
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun CurrencyDetailOtherInfoWidget_Preview() {
    AppTheme {
        CurrencyDetailOtherInfoWidget(
            model = CurrencyDetail.template,
        )
    }
}
