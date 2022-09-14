package com.marazmone.crypton.android.presentation.screen.currency.detail.composable

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrencyDetailDirection(
    id: String,
    navController: NavController,
    viewModel: CurrencyDetailViewModel = getViewModel(),
) {
    LaunchedEffect(id) {
        viewModel.getCurrencyById(id)
    }
    BackHandler {
        navController.popBackStack()
    }
    CurrencyDetailScreen(
        state = viewModel.state.value,
        onCloseClick = {
            navController.popBackStack()
        },
        onChangeFavorite = { isFavorite ->
            viewModel.setFavorite(id, isFavorite)
        },
    )
}