package com.marazmone.crypton.android.presentation.screen.list

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
import com.marazmone.crypton.android.presentation.ui.Main
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyItemComponent
import com.marazmone.crypton.android.presentation.util.PaddingValuesVertical
import com.marazmone.crypton.domain.model.CurrencyListItem
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun SuccessStatePreview() {
    val resultList = List(100) {
        CurrencyListItem(
            id = Random.nextInt().toString(),
            name = "Currency name",
            symbol = "SML",
            rank = it.plus(1),
            percentChange24H = Random.nextDouble(-90.0, 90.0).toFloat(),
            _price = Random.nextDouble(1.0, 50_000.0).toFloat(),
            _mCap = Random.nextDouble(100_000_000.0, 10_000_000_000.0).toFloat(),
            isFavorite = Random.nextBoolean(),
            imageUrl = "",
        )
    }

    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            LazyColumn(
                contentPadding = PaddingValuesVertical()
            ) {
                items(resultList.sortedBy { it.rank }) { item ->
                    CurrencyItemComponent(item = item)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingStatePreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Main.Background),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorStatePreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Main.Background),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Error: test error")
        }
    }
}