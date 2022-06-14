package com.marazmone.crypton.android.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.marazmone.crypton.android.presentation.ui.AppTheme
import com.marazmone.crypton.android.presentation.ui.Main.Background
import com.marazmone.crypton.android.presentation.ui.component.currency.CurrencyItemComponent
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen(
        viewModel: MainViewModel = getViewModel()
    ) {
        val state = viewModel.stateLiveData.value
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.isRefresh),
            onRefresh = { viewModel.getAllCurrency(true) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background),
                contentAlignment = Alignment.Center,
            ) {
                when {
                    state.isLoading -> CircularProgressIndicator()
                    state.isError -> Text(text = "Error: ${state.errorText}")
                    state.list.isNotEmpty() -> {
                        LazyColumn {
                            items(state.list) { item ->
                                CurrencyItemComponent(item = item)
                            }
                        }
                    }
                }
            }
        }
    }
}
