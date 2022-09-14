package com.marazmone.crypton.android.presentation.screen.currency.favorite

import androidx.lifecycle.viewModelScope
import com.marazmone.crypton.android.presentation.base.BaseViewModel
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteContract.Action
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteContract.Action.Error
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteContract.Action.Success
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteContract.Effect
import com.marazmone.crypton.android.presentation.screen.currency.favorite.CurrencyFavoriteContract.State
import com.marazmone.crypton.domain.usecase.currency.CurrencyObserveAllFavoriteUseCase
import com.marazmone.crypton.domain.usecase.currency.CurrencyUpdateByIdsUseCase
import kotlinx.coroutines.launch

class CurrencyFavoriteViewModel(
    private val observeAllFavoriteUseCase: CurrencyObserveAllFavoriteUseCase,
    private val updateByIdsUseCase: CurrencyUpdateByIdsUseCase,
) : BaseViewModel<State, Action, Effect>() {

    init {
        viewModelScope.launch {
            observeAllFavoriteUseCase.execute().collect {
                sendAction { Success(it) }
            }
        }
    }

    override fun setInitialState(): State = State()

    fun updateByIds() {
        viewModelScope.launch {
            sendAction { Action.Refresh }
            val ids = currentState.list.map { it.id }
            if (ids.isNotEmpty()) {
                runCatching {
                    updateByIdsUseCase.execute(ids)
                }.onFailure {
                    sendAction { Error(it.localizedMessage.orEmpty()) }
                }
            }
        }
    }

    override fun onReduceState(action: Action): State = when (action) {
        is Success -> currentState.copy(
            list = action.list,
            isError = false,
            errorText = "",
            isRefresh = false
        )
        is Error -> currentState.copy(
            isError = true,
            errorText = action.message,
            isRefresh = false
        )
        is Action.Refresh -> currentState.copy(isRefresh = true)
    }


}