package com.marazmone.crypton.android.presentation.screen.currency.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marazmone.crypton.android.presentation.ui.AppTheme
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