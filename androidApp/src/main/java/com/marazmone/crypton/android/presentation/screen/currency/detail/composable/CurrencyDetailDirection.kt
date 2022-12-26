package com.marazmone.crypton.android.presentation.screen.currency.detail.composable

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailViewModel
import com.marazmone.crypton.android.presentation.util.openUrlFromBrowser
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrencyDetailDirection(
    id: String,
    navController: NavController,
    viewModel: CurrencyDetailViewModel = getViewModel(),
) {
    val context = LocalContext.current

    LaunchedEffect(id) {
        viewModel.getCurrencyById(id)
    }
    BackHandler {
        closeScreen(viewModel, navController)
    }
    CurrencyDetailScreen(
        state = viewModel.state.value,
        onCloseClick = {
            closeScreen(viewModel, navController)
        },
        onChangeFavorite = { isFavorite ->
            viewModel.setFavorite(id, isFavorite)
        },
        onOpenTwitterPage = { symbol ->
            context.openUrlFromBrowser("https://twitter.com/search?q=$$symbol")
        }
    )
}

private fun closeScreen(
    viewModel: CurrencyDetailViewModel,
    navController: NavController,
) {
    viewModel.hideTwitterButton()
    navController.popBackStack()
}
