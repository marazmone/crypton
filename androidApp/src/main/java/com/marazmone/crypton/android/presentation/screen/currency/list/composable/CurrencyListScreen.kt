package com.marazmone.crypton.android.presentation.screen.currency.list.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.State
import com.marazmone.crypton.android.presentation.ui.Colors
import com.marazmone.crypton.android.presentation.ui.component.common.ListUpFloatingActionButton
import com.marazmone.crypton.android.presentation.ui.component.common.PullRefresh
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyItemComponent
import com.marazmone.crypton.android.presentation.ui.component.state.ImageWithTextActionStateComponent
import com.marazmone.crypton.android.presentation.ui.component.util.isScrollingBottom
import com.marazmone.crypton.android.presentation.ui.component.util.isScrollingUp
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
                ImageWithTextActionStateComponent(
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
