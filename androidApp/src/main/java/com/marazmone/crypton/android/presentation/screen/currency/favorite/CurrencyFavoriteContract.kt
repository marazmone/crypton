package com.marazmone.crypton.android.presentation.screen.currency.favorite

import com.marazmone.crypton.android.presentation.base.BaseViewAction
import com.marazmone.crypton.android.presentation.base.BaseViewEffect
import com.marazmone.crypton.android.presentation.base.BaseViewState
import com.marazmone.crypton.domain.model.currency.CurrencyListItem

class CurrencyFavoriteContract {

    data class State(
        val list: List<CurrencyListItem> = listOf(),
        val isError: Boolean = false,
        val errorText: String = "",
        val isRefresh: Boolean = false,
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        class Success(val list: List<CurrencyListItem>) : Action

        class Error(val message: String) : Action

        object Refresh : Action
    }

    sealed interface Effect : BaseViewEffect
}