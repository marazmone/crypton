package com.marazmone.crypton.android.presentation

import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.android.presentation.base.BaseAction
import com.marazmone.crypton.android.presentation.base.BaseViewModel
import com.marazmone.crypton.android.presentation.base.BaseViewState
import com.marazmone.crypton.domain.model.CurrencyListItem
import com.marazmone.crypton.domain.usecase.CurrencyGetAllUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val currencyGetAllUseCase: CurrencyGetAllUseCase,
) : BaseViewModel<MainViewModel.ViewState, MainViewModel.Action>(ViewState()) {

    init {
        getAllCurrency()
    }

    fun getAllCurrency(withRefresh: Boolean = false) {
        viewModelScope.launch {
            if (withRefresh) sendAction(Action.Refresh) else sendAction(Action.Loading)
            runCatching {
                currencyGetAllUseCase.execute()
            }.onSuccess {
                sendAction(Action.Success(it))
            }.onFailure {
                sendAction(Action.Error(it.message.orEmpty()))
            }
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