package com.marazmone.crypton.android.presentation.screen.currency.detail

import com.marazmone.crypton.android.presentation.base.BaseViewAction
import com.marazmone.crypton.android.presentation.base.BaseViewEffect
import com.marazmone.crypton.android.presentation.base.BaseViewState
import com.marazmone.crypton.domain.model.currency.CurrencyDetail

class CurrencyDetailContract {

    data class State(
        val isError: Boolean = false,
        val data: CurrencyDetail? = null
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        class Success(val data: CurrencyDetail) : Action

        class Favorite(val enable: Boolean) : Action

        object Error : Action
    }

    sealed interface Effect : BaseViewEffect
}
