package com.marazmone.crypton.android.presentation.screen.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyDetailPriceComponent
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyToolbarComponent
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
                modifier = Modifier.fillMaxSize()
            ) {
                CurrencyToolbarComponent(
                    model = state.data,
                    onCloseAction = {
                        navController.popBackStack()
                    },
                    onChangeFavoriteAction = {
                        viewModel.setFavorite(id, it)
                    }
                )
                LazyColumn {
                    item { CurrencyDetailPriceComponent(state.data) }
                }
            }
        }
        state.isError -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(text = "Error")
            }
        }
    }
}

