package com.marazmone.crypton.domain.usecase

import com.marazmone.crypton.domain.model.CurrencyListItem
import com.marazmone.crypton.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class CurrencyObserveAllUseCase(
    private val repository: CurrencyRepository,
) {

    fun execute(): Flow<List<CurrencyListItem>> = repository.observeAll()
}