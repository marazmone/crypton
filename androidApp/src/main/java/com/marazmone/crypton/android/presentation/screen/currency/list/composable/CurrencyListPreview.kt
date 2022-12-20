package com.marazmone.crypton.android.presentation.screen.currency.list.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marazmone.crypton.android.R.string
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.State
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.domain.model.currency.CurrencyListItem

@Preview(showBackground = true)
@Composable
private fun SuccessStatePreview() {
    AppTheme {
        CurrencyListScreen(
            state = State(
                list = CurrencyListItem.mockList().sortedBy { it.rank }
            ),
            onRefresh = {},
            onOpenDetailScreenAction = {},
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF11124B,
)
@Composable
private fun LoadingStatePreview() {
    AppTheme {
        CurrencyListScreen(
            state = State(
                isLoading = true,
            ),
            onRefresh = {},
            onOpenDetailScreenAction = {},
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
        CurrencyListScreen(
            state = State(
                isError = true,
                errorText = stringResource(id = string.something_wrong)
            ),
            onRefresh = {},
            onOpenDetailScreenAction = {},
        )
    }
}
