package com.marazmone.crypton.android.presentation.screen.currency.favorite.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteContract.State
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.domain.model.currency.CurrencyListItem

@Preview(showBackground = true)
@Composable
private fun SuccessStatePreview() {
    AppTheme {
        CurrencyFavoriteScreen(
            state = State(
                list = CurrencyListItem.mockList().sortedBy { it.rank },
            ),
            onRefresh = {},
            onOpenDetailScreen = {},
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF11124B
)
@Composable
private fun ErrorStatePreview() {
    AppTheme {
        CurrencyFavoriteScreen(
            state = State(
                isError = true,
                errorText = stringResource(id = R.string.something_wrong),
            ),
            onRefresh = {},
            onOpenDetailScreen = {},
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF11124B
)
@Composable
private fun EmptyStatePreview() {
    AppTheme {
        CurrencyFavoriteScreen(
            state = State(
                list = emptyList()
            ),
            onRefresh = {},
            onOpenDetailScreen = {},
        )
    }
}