package com.marazmone.crypton.android.presentation.screen.currency.list.composable

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.State
import com.marazmone.crypton.android.presentation.ui.component.common.ListUpFloatingButtonWidget
import com.marazmone.crypton.android.presentation.ui.component.common.PullRefreshWidget
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyItemWidget
import com.marazmone.crypton.android.presentation.ui.component.state.ImageWithTextActionStateWidget
import com.marazmone.crypton.android.presentation.ui.component.util.isScrollingBottom
import com.marazmone.crypton.android.presentation.util.PaddingValuesBottom
import com.marazmone.crypton.android.presentation.util.PaddingValuesVertical
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrencyListScreen(
    state: State,
    onRefresh: () -> Unit,
    onOpenDetailScreenAction: (currencyId: String) -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(state.isRefresh, onRefresh)
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.isError -> {
                ImageWithTextActionStateWidget(
                    resId = R.drawable.im_error_state,
                    text = state.errorText
                )
            }

            state.list.isNotEmpty() -> {
                LazyColumn(
                    state = lazyListState,
                    contentPadding = PaddingValuesVertical(),
                ) {
                    items(state.list) { item ->
                        CurrencyItemWidget(
                            item = item,
                            modifier = Modifier.clickable {
                                onOpenDetailScreenAction.invoke(item.id)
                            }
                        )
                    }
                }
            }
        }
        ListUpFloatingButtonWidget(
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
        PullRefreshWidget(
            state = pullRefreshState,
            refreshing = { state.isRefresh },
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
