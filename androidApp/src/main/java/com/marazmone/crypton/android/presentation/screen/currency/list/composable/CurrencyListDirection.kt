package com.marazmone.crypton.android.presentation.screen.currency.list.composable

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.marazmone.crypton.android.presentation.navigation.NavScreen.CurrencyDetail
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrencyListDirection(
    navController: NavController,
    viewModel: CurrencyListViewModel = getViewModel(),
) {
    CurrencyListScreen(
        state = viewModel.state.value,
        onRefresh = {
            viewModel.getAllCurrency(true)
        },
        onOpenDetailScreenAction = { id ->
            val route = CurrencyDetail.createRoute(id)
            navController.navigate(route)
        },
    )
}