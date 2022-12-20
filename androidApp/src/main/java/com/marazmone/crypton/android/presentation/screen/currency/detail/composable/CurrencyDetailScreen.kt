package com.marazmone.crypton.android.presentation.screen.currency.detail.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.State
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyDetailKeyFiguresComponent
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyDetailOtherInfoComponent
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyDetailPriceComponent
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyToolbarComponent
import com.marazmone.crypton.android.presentation.ui.component.state.ImageWithTextActionStateComponent

@Composable
fun CurrencyDetailScreen(
    state: State,
    onCloseClick: () -> Unit,
    onChangeFavorite: (Boolean) -> Unit,
) {
    when {
        state.data != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                CurrencyToolbarComponent(
                    model = state.data,
                    onCloseAction = {
                        onCloseClick.invoke()
                    },
                    onChangeFavoriteAction = { isFavorite ->
                        onChangeFavorite.invoke(isFavorite)
                    },
                )
                LazyColumn {
                    item { CurrencyDetailPriceComponent(state.data) }
                    item { CurrencyDetailKeyFiguresComponent(state.data) }
                    item { CurrencyDetailOtherInfoComponent(state.data) }
                }
            }
        }
        state.isError -> {
            ImageWithTextActionStateComponent(
                resId = R.drawable.im_error_state,
                text = stringResource(id = R.string.something_wrong),
            )
        }
    }
}
