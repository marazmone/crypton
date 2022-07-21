package com.marazmone.crypton.android.presentation.screen.currency.list

import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.android.presentation.base.BaseAction
import com.marazmone.crypton.android.presentation.base.BaseViewModel
import com.marazmone.crypton.android.presentation.base.BaseViewState
import com.marazmone.crypton.android.presentation.model.currency.CurrencyChange
import com.marazmone.crypton.android.presentation.notification.DailyCurrencyRateChangesMessage
import com.marazmone.crypton.android.presentation.notification.NotificationCreator
import com.marazmone.crypton.domain.model.currency.CurrencyListItem
import com.marazmone.crypton.domain.usecase.currency.CurrencyGetAllUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyObserveAllUseCase
import kotlin.math.absoluteValue
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CurrencyListViewModel(
    private val currencyGetAllUseCase: CurrencyGetAllUseCase,
    private val currencyObserveAllUseCase: CurrencyObserveAllUseCase,
    private val notificationCreator: NotificationCreator,
) : BaseViewModel<CurrencyListViewModel.ViewState, CurrencyListViewModel.Action>(ViewState()) {

    init {
        getAllCurrency()
        observeAllCurrency()
    }

    fun getAllCurrency(withRefresh: Boolean = false) {
        viewModelScope.launch {
            if (withRefresh) sendAction(Action.Refresh) else sendAction(Action.Loading)
            runCatching {
                currencyGetAllUseCase.execute()
            }.onFailure {
                sendAction(Action.Error(it.message.orEmpty()))
            }
        }
    }

    private fun observeAllCurrency() {
        viewModelScope.launch {
            currencyObserveAllUseCase.execute().collect { list ->
                if (list.isNotEmpty()) {
                    sendAction(Action.Success(list))
                    testNotification(list)
                }
            }
        }
    }

    // TODO: for test
    @Deprecated(message = "Need remove after test notification", level = DeprecationLevel.WARNING)
    private fun testNotification(list: List<CurrencyListItem>) {
        val onlyFavoriteCurrencies = list.filter { it.isFavorite }
            .filter { it.percentChange24H.absoluteValue >= 5.0 }
            .map {
                CurrencyChange(
                    symbol = it.symbol,
                    percentChange24H = it.percentChange24H,
                    isGrewUp = it.percentChange24H > 0
                )
            }
        if (onlyFavoriteCurrencies.isNotEmpty()) {
            val json = Json.encodeToString(onlyFavoriteCurrencies)
            notificationCreator.showNotification(
                DailyCurrencyRateChangesMessage(
                    title = "Daily currency rate changes",
                    body = json,
                )
            )
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.Success -> state.copy(
            isRefresh = false,
            isLoading = false,
            isError = false,
            list = viewAction.list,
        )
        is Action.Error -> state.copy(
            isRefresh = false,
            isLoading = false,
            isError = true,
            errorText = viewAction.message,
            list = emptyList(),
        )
        is Action.Loading -> state.copy(isLoading = true)
        is Action.Refresh -> state.copy(isRefresh = true)
    }

    data class ViewState(
        val isRefresh: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val errorText: String = "",
        val list: List<CurrencyListItem> = listOf()
    ) : BaseViewState

    sealed interface Action : BaseAction {

        class Success(val list: List<CurrencyListItem>) : Action

        class Error(val message: String) : Action

        object Refresh : Action

        object Loading : Action
    }
}