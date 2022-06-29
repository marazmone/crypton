package com.marazmone.crypton.android.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Main
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyDetailPriceComponent
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyToolbarComponent
import com.marazmone.crypton.domain.model.currency.CurrencyDetail

@Preview(showBackground = true)
@Composable
private fun SuccessStatePreview() {
    AppTheme {
        val model = CurrencyDetail.empty
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Main.Background),
        ) {
            CurrencyToolbarComponent(model)
            LazyColumn {
                item { CurrencyDetailPriceComponent(model) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorStatePreview() {
    AppTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Main.Background),
        ) {
            Text(text = "Error")
        }
    }
}