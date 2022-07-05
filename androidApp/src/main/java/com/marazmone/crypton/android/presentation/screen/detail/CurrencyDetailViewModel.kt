package com.marazmone.crypton.android.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.android.presentation.base.BaseAction
import com.marazmone.crypton.android.presentation.base.BaseViewModel
import com.marazmone.crypton.android.presentation.base.BaseViewState
import com.marazmone.crypton.domain.model.currency.CurrencyDetail
import com.marazmone.crypton.domain.usecase.CurrencyGetByIdUseCase
import com.marazmone.crypton.domain.usecase.CurrencySetFavoriteByIdUseCase
import kotlinx.coroutines.launch

class CurrencyDetailViewModel(
    private val currencyGetByIdUseCase: CurrencyGetByIdUseCase,
    private val currencyUpdateFavoriteByIdUseCase: CurrencySetFavoriteByIdUseCase,
) : BaseViewModel<CurrencyDetailViewModel.ViewState, CurrencyDetailViewModel.Action>(ViewState()) {

    fun getCurrencyById(id: String) {
        viewModelScope.launch {
            val result = currencyGetByIdUseCase.execute(id)
            if (result != null) {
                sendAction(Action.Success(result))
            } else {
                sendAction(Action.Error)
            }
        }
    }

    fun setFavorite(id: String, isFavorite: Boolean) {
        viewModelScope.launch {
            currencyUpdateFavoriteByIdUseCase.execute(id, isFavorite)
            sendAction(Action.Favorite(isFavorite))
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.Error -> state.copy(
            isError = true,
            data = null,
        )
        is Action.Success -> state.copy(
            isError = false,
            data = viewAction.data
        )
        is Action.Favorite -> state.copy(
            data = state.data?.copy(isFavorite = viewAction.enable)
        )
    }

    data class ViewState(
        val isError: Boolean = false,
        val data: CurrencyDetail? = null
    ) : BaseViewState

    sealed interface Action : BaseAction {

        class Success(val data: CurrencyDetail) : Action

        class Favorite(val enable: Boolean) : Action

        object Error : Action
    }
}