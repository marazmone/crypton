package com.marazmone.crypton.android.presentation.screen.currency.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyDetailKeyFiguresComponent
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyDetailOtherInfoComponent
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyDetailPriceComponent
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyToolbarComponent
import com.marazmone.crypton.android.presentation.ui.component.state.ImageWithTextActionStateComponent
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrencyDetailScreen(
    id: String,
    navController: NavController,
    viewModel: CurrencyDetailViewModel = getViewModel(),
) {
    val state = viewModel.stateLiveData.value

    LaunchedEffect(key1 = id) {
        viewModel.getCurrencyById(id)
    }

    BackHandler {
        navController.popBackStack()
    }

    when {
        state.data != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                CurrencyToolbarComponent(
                    model = state.data,
                    onCloseAction = {
                        navController.popBackStack()
                    },
                    onChangeFavoriteAction = {
                        viewModel.setFavorite(id, it)
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

