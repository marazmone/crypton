package com.marazmone.crypton.android.presentation.screen.currency.list.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.State
import com.marazmone.crypton.android.presentation.ui.Colors
import com.marazmone.crypton.android.presentation.ui.component.common.PullRefresh
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyItemComponent
import com.marazmone.crypton.android.presentation.ui.component.state.ImageWithTextActionStateComponent
import com.marazmone.crypton.android.presentation.util.PaddingValuesVertical

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrencyListScreen(
    state: State,
    onRefresh: () -> Unit,
    onOpenDetailScreenAction: (currencyId: String) -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(state.isRefresh, onRefresh)
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
                ImageWithTextActionStateComponent(
                    resId = R.drawable.im_error_state,
                    text = state.errorText
                )
            }

            state.list.isNotEmpty() -> {
                LazyColumn(
                    contentPadding = PaddingValuesVertical(),
                ) {
                    items(state.list) { item ->
                        CurrencyItemComponent(
                            item = item,
                            modifier = Modifier.clickable {
                                onOpenDetailScreenAction.invoke(item.id)
                            }
                        )
                    }
                }
            }
        }
        PullRefresh(
            state = pullRefreshState,
            refreshing = { state.isRefresh },
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}