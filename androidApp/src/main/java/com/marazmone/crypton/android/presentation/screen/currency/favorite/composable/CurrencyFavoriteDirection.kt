package com.marazmone.crypton.android.presentation.screen.currency.favorite.composable

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.marazmone.crypton.android.presentation.navigation.NavScreen
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrencyFavoriteDirection(
    navController: NavController,
    viewModel: CurrencyFavoriteViewModel = getViewModel()
) {
    CurrencyFavoriteScreen(
        state = viewModel.state.value,
        onRefresh = {
            viewModel.updateByIds()
        },
        onOpenDetailScreen = { id ->
            val route = NavScreen.CurrencyDetail.createRoute(id)
            navController.navigate(route)
        }
    )
}