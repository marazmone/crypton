package com.marazmone.crypton.android.presentation.screen.currency.list

import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.android.presentation.base.BaseViewModel
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.Action
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.Action.Error
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.Action.Loading
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.Action.Refresh
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.Action.Success
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.Effect
import com.marazmone.crypton.android.presentation.screen.currency.list.CurrencyListContract.State
import com.marazmone.crypton.domain.usecase.currency.CurrencyGetAllUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyObserveAllUseCase
import kotlinx.coroutines.launch

class CurrencyListViewModel(
    private val currencyGetAllUseCase: CurrencyGetAllUseCase,
    private val currencyObserveAllUseCase: CurrencyObserveAllUseCase,
) : BaseViewModel<State, Action, Effect>() {

    init {
        getAllCurrency()
        observeAllCurrency()
    }

    override fun setInitialState(): State = State()

    fun getAllCurrency(withRefresh: Boolean = false) {
        viewModelScope.launch {
            sendAction {
                if (withRefresh) Refresh else Loading
            }
            runCatching {
                currencyGetAllUseCase.execute()
            }.onFailure {
                sendAction { Error(it.message.orEmpty()) }
            }
        }
    }

    private fun observeAllCurrency() {
        viewModelScope.launch {
            currencyObserveAllUseCase.execute().collect { list ->
                if (list.isNotEmpty()) sendAction { Success(list) }
            }
        }
    }

    override fun onReduceState(action: Action): State = when (action) {
        is Success -> currentState.copy(
            isRefresh = false,
            isLoading = false,
            isError = false,
            list = action.list,
        )
        is Error -> currentState.copy(
            isRefresh = false,
            isLoading = false,
            isError = true,
            errorText = action.message,
            list = emptyList(),
        )
        is Loading -> currentState.copy(isLoading = true)
        is Refresh -> currentState.copy(isRefresh = true)
    }
}