package com.marazmone.crypton.android.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.android.presentation.usecase.DailyRateCheckAlarmStartUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val dailyRateCheckAlarmStartUseCase: DailyRateCheckAlarmStartUseCase,
) : ViewModel() {

    fun startDailyRateChecker() {
        viewModelScope.launch {
            dailyRateCheckAlarmStartUseCase.execute()
        }
    }
}