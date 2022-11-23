package com.marazmone.crypton.android.presentation.ui.component.common

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.marazmone.crypton.android.presentation.ui.Colors.Main

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefresh(
    state: PullRefreshState,
    refreshing: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    PullRefreshIndicator(
        refreshing = refreshing.invoke(),
        state = state,
        backgroundColor = Main.BackgroundSecond,
        contentColor = Color.White,
        scale = true,
        modifier = modifier,
    )
}