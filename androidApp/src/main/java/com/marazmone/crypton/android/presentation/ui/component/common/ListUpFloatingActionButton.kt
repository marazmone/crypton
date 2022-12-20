package com.marazmone.crypton.android.presentation.ui.component.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.marazmone.crypton.android.presentation.ui.Colors.Main

private const val RotateArrow = 90f

@Composable
fun ListUpFloatingActionButton(
    extended: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = extended,
        modifier = modifier,
        enter = fadeIn() + slideInVertically(initialOffsetY = { 120.dp.value.toInt() }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { 120.dp.value.toInt() }),
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .rotate(RotateArrow)
                .background(Main.Button),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    }
}
