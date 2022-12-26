package com.marazmone.crypton.android.presentation.screen.currency.detail.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.R
import com.marazmone.crypton.android.R.drawable
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.State
import com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component.CurrencyDetailKeyFiguresWidget
import com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component.CurrencyDetailOtherInfoWidget
import com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component.CurrencyDetailPriceWidget
import com.marazmone.crypton.android.presentation.screen.currency.detail.composable.component.CurrencyToolbarComponent
import com.marazmone.crypton.android.presentation.ui.component.state.ImageWithTextActionStateWidget

@Composable
fun CurrencyDetailScreen(
    state: State,
    onCloseClick: () -> Unit,
    onChangeFavorite: (Boolean) -> Unit,
    onOpenTwitterPage: (symbol: String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when {
            state.data != null -> {
                Column {
                    CurrencyToolbarComponent(
                        model = state.data,
                        onCloseAction = {
                            onCloseClick.invoke()
                        },
                        onChangeFavoriteAction = { isFavorite ->
                            onChangeFavorite.invoke(isFavorite)
                        },
                    )
                    LazyColumn {
                        item { CurrencyDetailPriceWidget(state.data) }
                        item { CurrencyDetailKeyFiguresWidget(state.data) }
                        item { CurrencyDetailOtherInfoWidget(state.data) }
                    }
                }
            }

            state.isError -> {
                ImageWithTextActionStateWidget(
                    resId = R.drawable.im_error_state,
                    text = stringResource(id = R.string.something_wrong),
                )
            }
        }

        AnimatedVisibility(
            visible = state.twButtonVisibility,
            enter = fadeIn(),
            exit = slideOutVertically(
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
            ).plus(
                fadeOut(
                    animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
                )
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd),
        ) {
            IconButton(
                onClick = {
                    state.data
                        ?.symbol
                        ?.also(onOpenTwitterPage)
                },
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 16.dp)
                    .size(60.dp)
                    .clip(CircleShape),
            ) {
                Image(
                    painter = painterResource(id = drawable.ic_twitter),
                    contentDescription = null
                )
            }
        }
    }
}
