package com.marazmone.crypton.android.presentation.screen.currency.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Colors.Main.Background
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyItemComponent
import com.marazmone.crypton.android.presentation.util.PaddingValuesVertical
import com.marazmone.crypton.domain.model.currency.CurrencyListItem

@Preview(showBackground = true)
@Composable
private fun SuccessStatePreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            LazyColumn(
                contentPadding = PaddingValuesVertical()
            ) {
                items(CurrencyListItem.emptyList().sortedBy { it.rank }) { item ->
                    CurrencyItemComponent(item = item)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingStatePreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Background),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorStatePreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Background),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Error: test error")
        }
    }
}