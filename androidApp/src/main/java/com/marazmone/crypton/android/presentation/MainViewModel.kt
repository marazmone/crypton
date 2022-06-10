package com.marazmone.crypton.android.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.domain.usecase.CurrencyGetAllUseCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch

class MainViewModel(
    private val currencyGetAllUseCase: CurrencyGetAllUseCase,
) : ViewModel() {

    fun getAllCurrency() {
        viewModelScope.launch {
            runCatching {
                currencyGetAllUseCase.execute()
            }.onSuccess {
                Log.d("MainViewModel", "onSuccess list: $it")
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}