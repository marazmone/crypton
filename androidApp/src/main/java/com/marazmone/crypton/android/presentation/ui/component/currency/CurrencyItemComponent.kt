package com.marazmone.crypton.android.presentation.ui.component.currency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Main.Background
import com.marazmone.crypton.android.presentation.ui.Main.BackgroundSecond
import com.marazmone.crypton.android.presentation.ui.component.common.PercentChangeComponent
import com.marazmone.crypton.domain.model.CurrencyListItem

@Composable
fun CurrencyItemComponent(
    item: CurrencyListItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(Background)
            .fillMaxWidth()
            .height(64.dp)
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            SubcomposeAsyncImage(
                model = item.imageUrl,
                loading = {
                    CircularProgressIndicator()
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(BackgroundSecond)
                    )
                },
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
            )
        }
        Column(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.name,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PercentChangeComponent(
                percentChange = item.percentChange24H,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                textStyle = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp)
            )
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                )
                Text(
                    text = item.mCap(stringResource(id = R.string.market_cap)),
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.White),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyItemUPPreview() {
    AppTheme {
        CurrencyItemComponent(
            CurrencyListItem(
                id = "1",
                name = "Bitcoin",
                symbol = "BTC",
                rank = 1,
                percentChange24H = -2.3f,
                _price = 50123.12321f,
                _mCap = 1126003742478.3057f,
                isFavorite = false,
                imageUrl = "",
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyItemDownPreview() {
    AppTheme {
        CurrencyItemComponent(
            CurrencyListItem(
                id = "1",
                name = "Bitcoin",
                symbol = "BTC",
                rank = 1,
                percentChange24H = 2.3f,
                _price = 50123.12321f,
                _mCap = 1126003742478.3057f,
                isFavorite = false,
                imageUrl = "",
            )
        )
    }
}