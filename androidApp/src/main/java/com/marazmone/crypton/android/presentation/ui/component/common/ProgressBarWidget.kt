package com.marazmone.crypton.android.presentation.ui.component.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Colors.Gradient

@Composable
fun ProgressBarWidget(
    width: Dp,
    backgroundColor: Color,
    foregroundColor: Brush,
    percent: Int,
    modifier: Modifier = Modifier,
) {
    Box {
        Box(
            modifier = modifier
                .background(backgroundColor)
                .width(width)
        )
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percent / 100)
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun ProgressBarWidget_Preview() {
    AppTheme {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        ProgressBarWidget(
            width = screenWidth,
            backgroundColor = Color.White,
            foregroundColor = Brush.horizontalGradient(listOf(Gradient.LightStart, Gradient.LightEnd)),
            percent = 80,
            modifier = Modifier
                .clip(shape = CircleShape)
                .height(12.dp),
        )
    }
}
