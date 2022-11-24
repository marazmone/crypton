package com.marazmone.crypton.android.presentation.screen.currency.favorite.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteContract
import com.marazmone.crypton.android.presentation.ui.component.common.ListUpFloatingActionButton
import com.marazmone.crypton.android.presentation.ui.component.common.PullRefresh
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyItemComponent
import com.marazmone.crypton.android.presentation.ui.component.state.ImageWithTextActionStateComponent
import com.marazmone.crypton.android.presentation.ui.component.util.isScrollingBottom
import com.marazmone.crypton.android.presentation.util.PaddingValuesBottom
import com.marazmone.crypton.android.presentation.util.PaddingValuesVertical
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrencyFavoriteScreen(
    state: CurrencyFavoriteContract.State,
    onRefresh: () -> Unit,
    onOpenDetailScreen: (currencyId: String) -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(state.isRefresh, onRefresh)
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
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
                    state = lazyListState,
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
        ListUpFloatingActionButton(
            extended = lazyListState.isScrollingBottom(),
            modifier = Modifier
                .padding(PaddingValuesBottom())
                .padding(end = 16.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                scope.launch {
                    lazyListState.scrollToItem(0)
                }
            }
        )
        PullRefresh(
            state = pullRefreshState,
            refreshing = { state.isRefresh },
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}