package com.marazmone.crypton.android.presentation.screen.currency.list

import com.marazmone.crypton.android.presentation.base.BaseViewAction
import com.marazmone.crypton.android.presentation.base.BaseViewEffect
import com.marazmone.crypton.android.presentation.base.BaseViewState
import com.marazmone.crypton.domain.model.currency.CurrencyListItem

class CurrencyListContract {

    data class State(
        val isRefresh: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val errorText: String = "",
        val list: List<CurrencyListItem> = listOf()
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        class Success(val list: List<CurrencyListItem>) : Action

        class Error(val message: String) : Action

        object Refresh : Action

        object Loading : Action
    }

    sealed interface Effect : BaseViewEffect
}