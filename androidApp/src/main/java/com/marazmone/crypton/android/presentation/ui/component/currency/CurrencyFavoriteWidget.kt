package com.marazmone.crypton.android.presentation.ui.component.currency

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.ui.AppTheme

@Composable
fun CurrencyFavoriteWidget(
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onActionClick: (() -> Unit)? = null,
) {
    val iconRes = if (isFavorite) {
        R.drawable.ic_star_filled
    } else {
        R.drawable.ic_star_empty
    }
    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = null,
        tint = Color.White,
        modifier = modifier
            .size(28.dp)
            .clickable {
                onActionClick?.invoke()
            }
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun CurrencyFavoriteWidget_Enable_Preview() {
    AppTheme {
        CurrencyFavoriteWidget(
            isFavorite = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CurrencyFavoriteWidget_Disable_Preview() {
    AppTheme {
        CurrencyFavoriteWidget(
            isFavorite = false,
        )
    }
}
