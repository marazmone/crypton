package com.marazmone.crypton.domain.usecase

import com.marazmone.crypton.domain.model.currency.CurrencyDetail
import com.marazmone.crypton.domain.model.currency.CurrencyListItem
import com.marazmone.crypton.domain.repository.CurrencyRepository

class CurrencyGetByIdUseCase(
    private val repository: CurrencyRepository,
) {

    suspend fun execute(id: String): CurrencyDetail? = repository.getCurrencyById(id)
}