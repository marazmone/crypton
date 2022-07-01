package com.marazmone.crypton.android.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.marazmone.crypton.android.presentation.ui.Colors.Main.Background

private val MainColorScheme = lightColorScheme(
    background = Background,
)

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