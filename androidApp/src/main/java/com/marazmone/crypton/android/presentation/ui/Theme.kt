package com.marazmone.crypton.android.presentation.ui

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.marazmone.crypton.android.presentation.ui.Colors.Main.Background

private val MainColorScheme = lightColorScheme(
    background = Background,
)

object NoRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MainColorScheme,
        typography = Typography,
        content = content
    )
}