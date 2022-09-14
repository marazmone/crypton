package com.marazmone.crypton.android.presentation.screen.currency.detail.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.State
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.domain.model.currency.CurrencyDetail

@Preview(
    showBackground = true,
)
@Composable
private fun SuccessStatePreview() {
    AppTheme {
        CurrencyDetailScreen(
            state = State(
                data = CurrencyDetail.empty,
            ),
            onCloseClick = {},
            onChangeFavorite = {},
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF11124B,
)
@Composable
private fun ErrorStatePreview() {
    AppTheme {
        CurrencyDetailScreen(
            state = State(
                isError = true
            ),
            onCloseClick = {},
            onChangeFavorite = {},
        )
    }
}