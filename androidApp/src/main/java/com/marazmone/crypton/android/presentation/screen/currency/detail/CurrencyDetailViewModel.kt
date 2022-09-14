package com.marazmone.crypton.android.presentation.screen.currency.detail

import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.android.presentation.base.BaseViewModel
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.Action
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.Action.Error
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.Action.Favorite
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.Action.Success
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.Effect
import com.marazmone.crypton.android.presentation.screen.currency.detail.CurrencyDetailContract.State
import com.marazmone.crypton.domain.usecase.currency.CurrencyGetByIdUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencySetFavoriteByIdUseCase
import kotlinx.coroutines.launch

class CurrencyDetailViewModel(
    private val currencyGetByIdUseCase: CurrencyGetByIdUseCase,
    private val currencyUpdateFavoriteByIdUseCase: CurrencySetFavoriteByIdUseCase,
) : BaseViewModel<State, Action, Effect>() {

    override fun setInitialState(): State = State()

    fun getCurrencyById(id: String) {
        viewModelScope.launch {
            val result = currencyGetByIdUseCase.execute(id)
            sendAction {
                if (result != null) {
                    Success(result)
                } else {
                    Error
                }
            }
        }
    }

    fun setFavorite(id: String, isFavorite: Boolean) {
        viewModelScope.launch {
            currencyUpdateFavoriteByIdUseCase.execute(id, isFavorite)
            sendAction { Favorite(isFavorite) }
        }
    }

    override fun onReduceState(action: Action): State = when (action) {
        is Error -> currentState.copy(
            isError = true,
            data = null,
        )
        is Success -> currentState.copy(
            isError = false,
            data = action.data
        )
        is Favorite -> currentState.copy(
            data = currentState.data?.copy(isFavorite = action.enable)
        )
    }
}