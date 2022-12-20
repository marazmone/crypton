package com.marazmone.crypton.domain.usecase.currency

import com.marazmone.crypton.domain.model.currency.CurrencyListItem
import com.marazmone.crypton.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class CurrencyObserveAllUseCase(
    private val repository: CurrencyRepository,
) {

    fun execute(): Flow<List<CurrencyListItem>> = repository.observeAll()
}
