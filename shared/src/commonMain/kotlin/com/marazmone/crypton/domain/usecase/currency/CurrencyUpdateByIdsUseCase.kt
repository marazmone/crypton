package com.marazmone.crypton.domain.usecase.currency

import com.marazmone.crypton.domain.model.currency.CurrencyListItem
import com.marazmone.crypton.domain.repository.CurrencyRepository

class CurrencyUpdateByIdsUseCase(
    private val repository: CurrencyRepository,
) {

    suspend fun execute(ids: List<String>): List<CurrencyListItem> = repository.updateByIds(ids)
}
