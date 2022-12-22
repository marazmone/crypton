package com.marazmone.crypton.android.presentation.screen.currency.detail.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.State
import com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component.CurrencyDetailKeyFiguresWidget
import com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component.CurrencyDetailOtherInfoWidget
import com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component.CurrencyDetailPriceWidget
import com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component.CurrencyToolbarComponent
import com.marazmone.crypton.android.presentation.ui.component.state.ImageWithTextActionStateWidget

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
                    item { CurrencyDetailPriceWidget(state.data) }
                    item { CurrencyDetailKeyFiguresWidget(state.data) }
                    item { CurrencyDetailOtherInfoWidget(state.data) }
                }
            }
        }
        state.isError -> {
            ImageWithTextActionStateWidget(
                resId = R.drawable.im_error_state,
                text = stringResource(id = R.string.something_wrong),
            )
        }
    }
}
