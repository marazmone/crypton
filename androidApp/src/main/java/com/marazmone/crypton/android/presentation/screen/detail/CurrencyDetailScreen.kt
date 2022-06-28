package com.marazmone.crypton.android.presentation.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrencyDetailScreen(
    id: String,
    navController: NavController,
    viewModel: CurrencyDetailViewModel = getViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ID: $id",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
    }
}