package com.marazmone.crypton.android.presentation.ui.component.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Colors.Main

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshWidget(
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

@OptIn(ExperimentalMaterialApi::class)
@Preview(
    showBackground = true,
)
@Composable
private fun PullRefreshWidget_Preview() {
    AppTheme {
        val pullRefreshState = rememberPullRefreshState(
            refreshing = true,
            onRefresh = {},
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState),
        ) {
            PullRefreshWidget(
                state = pullRefreshState,
                refreshing = { true },
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}
