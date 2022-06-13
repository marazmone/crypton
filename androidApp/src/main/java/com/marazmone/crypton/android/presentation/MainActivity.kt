package com.marazmone.crypton.android.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen(
        viewModel: MainViewModel = getViewModel()
    ) {
        val state = viewModel.stateLiveData.value
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { viewModel.getAllCurrency() }) {
                Text(text = "Refresh!")
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                when {
                    state.isLoading -> Text(text = "Loading")
                    state.isError -> Text(text = "Error: ${state.errorText}")
                    state.list.isNotEmpty() -> {
                        LazyColumn {
                            items(state.list) { item ->
                                Text(text = item.name.orEmpty())
                                Divider()
                            }
                        }
                    }
                }
            }
        }
    }
}
