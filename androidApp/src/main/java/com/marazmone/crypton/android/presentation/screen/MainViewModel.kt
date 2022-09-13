package com.marazmone.crypton.android.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.android.presentation.usecase.DailyRateCheckStartUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val dailyRateCheckStartUseCase: DailyRateCheckStartUseCase,
) : ViewModel() {

    fun startDailyRateChecker() {
        viewModelScope.launch {
            dailyRateCheckStartUseCase.execute()
        }
    }
}