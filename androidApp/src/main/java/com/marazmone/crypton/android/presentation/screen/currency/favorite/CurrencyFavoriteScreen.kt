package com.marazmone.crypton.android.presentation.screen.currency.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.marazmone.crypton.android.presentation.navigation.NavScreen
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyItemComponent
import com.marazmone.crypton.android.presentation.util.PaddingValuesVertical
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrencyFavoriteScreen(
    navController: NavController,
    viewModel: CurrencyFavoriteViewModel = getViewModel()
) {
    val state = viewModel.stateLiveData.value
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isRefresh),
        onRefresh = { viewModel.updateByIds() },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when {
                state.isError -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(text = "Error: ${state.errorText}")
                    }
                }
                state.list.isEmpty() -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(text = "Favorite list is empty.\nPlease select any currency.")
                    }
                }
                else -> {
                    LazyColumn(
                        contentPadding = PaddingValuesVertical(),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(state.list) { item ->
                            CurrencyItemComponent(
                                item = item,
                                modifier = Modifier.clickable {
                                    val route = NavScreen.CurrencyDetail.createRoute(item.id)
                                    navController.navigate(route)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}