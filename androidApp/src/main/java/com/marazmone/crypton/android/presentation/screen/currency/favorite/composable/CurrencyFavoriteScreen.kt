package com.marazmone.crypton.android.presentation.screen.currency.favorite.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteContract
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyItemComponent
import com.marazmone.crypton.android.presentation.ui.component.state.ImageWithTextActionStateComponent
import com.marazmone.crypton.android.presentation.util.PaddingValuesVertical

@Composable
fun CurrencyFavoriteScreen(
    state: CurrencyFavoriteContract.State,
    onRefresh: () -> Unit,
    onOpenDetailScreen: (currencyId: String) -> Unit,
) {

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isRefresh),
        onRefresh = onRefresh,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            when {
                state.isError -> {
                    ImageWithTextActionStateComponent(
                        resId = R.drawable.im_error_state,
                        text = state.errorText
                    )
                }
                state.list.isEmpty() -> {
                    ImageWithTextActionStateComponent(
                        resId = R.drawable.im_empty_state,
                        text = stringResource(id = R.string.favorite_empty_state)
                    )
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
                                    onOpenDetailScreen.invoke(item.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}