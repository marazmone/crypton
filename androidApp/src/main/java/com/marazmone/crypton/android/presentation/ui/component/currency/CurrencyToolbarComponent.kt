package com.marazmone.crypton.android.presentation.ui.component.currency

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Colors.Main.Background
import com.marazmone.crypton.android.presentation.ui.Colors.Main.BackgroundSecond
import com.marazmone.crypton.domain.model.currency.CurrencyDetail

private const val WeightSide = 20f
private const val WeightCenter = 80f

@Composable
fun CurrencyToolbarComponent(
    model: CurrencyDetail,
    onCloseAction: (() -> Unit)? = null,
    onChangeFavoriteAction: ((Boolean) -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(WeightSide),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        onCloseAction?.invoke()
                    }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(WeightCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                model = model.imageUrl,
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
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = model.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(WeightSide),
        ) {
            CurrencyFavoriteComponent(isFavorite = model.isFavorite) {
                onChangeFavoriteAction?.invoke(model.isFavorite.not())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CurrencyToolbarComponentPreview() {
    AppTheme {
        Box(modifier = Modifier.background(Background)) {
            CurrencyToolbarComponent(model = CurrencyDetail.empty)
        }
    }
}
