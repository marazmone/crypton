package com.marazmone.crypton.android.presentation.screen.currency.favorite

import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.android.presentation.base.BaseAction
import com.marazmone.crypton.android.presentation.base.BaseViewModel
import com.marazmone.crypton.android.presentation.base.BaseViewState
import com.marazmone.crypton.domain.model.currency.CurrencyListItem
import com.marazmone.crypton.domain.usecase.currency.CurrencyObserveAllFavoriteUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyUpdateByIdsUseCase
import kotlinx.coroutines.launch

class CurrencyFavoriteViewModel(
    private val observeAllFavoriteUseCase: CurrencyObserveAllFavoriteUseCase,
    private val updateByIdsUseCase: CurrencyUpdateByIdsUseCase,
) : BaseViewModel<CurrencyFavoriteViewModel.ViewState, CurrencyFavoriteViewModel.Action>(ViewState()) {

    init {
        viewModelScope.launch {
            observeAllFavoriteUseCase.execute().collect {
                sendAction(Action.Success(it))
            }
        }
    }

    fun updateByIds() {
        viewModelScope.launch {
            sendAction(Action.Refresh)
            val ids = state.list.map { it.id }
            if (ids.isNotEmpty()) {
                runCatching {
                    updateByIdsUseCase.execute(ids)
                }.onFailure {
                    sendAction(Action.Error(it.localizedMessage.orEmpty()))
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.Success -> state.copy(
            list = viewAction.list,
            isError = false,
            errorText = "",
            isRefresh = false
        )
        is Action.Error -> state.copy(
            isError = true,
            errorText = viewAction.message,
            isRefresh = false
        )
        is Action.Refresh -> state.copy(isRefresh = true)
    }

    data class ViewState(
        val list: List<CurrencyListItem> = listOf(),
        val isError: Boolean = false,
        val errorText: String = "",
        val isRefresh: Boolean = false,
    ) : BaseViewState

    sealed interface Action : BaseAction {

        class Success(val list: List<CurrencyListItem>) : Action

        class Error(val message: String) : Action

        object Refresh : Action
    }
}