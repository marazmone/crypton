package com.marazmone.crypton.android.presentation.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable

@Composable
fun PaddingValuesTop(): PaddingValues {
    return WindowInsets.statusBars.asPaddingValues()
}

@Composable
fun PaddingValuesBottom(): PaddingValues {
    return WindowInsets.navigationBars.asPaddingValues()
}

@Composable
fun PaddingValuesVertical(): PaddingValues {
    return WindowInsets.statusBars.add(WindowInsets.navigationBars).asPaddingValues()
}