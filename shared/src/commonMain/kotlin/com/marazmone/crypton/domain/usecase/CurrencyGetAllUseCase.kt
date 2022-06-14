package com.marazmone.crypton.domain.usecase

import com.marazmone.crypton.domain.model.CurrencyListItem
import com.marazmone.crypton.domain.repository.CurrencyRepository

class CurrencyGetAllUseCase(
    private val repository: CurrencyRepository,
) {

    suspend fun execute(): List<CurrencyListItem> = repository.getAll()
}