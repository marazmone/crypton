package com.marazmone.crypton.domain.usecase

import com.marazmone.crypton.data.remote.response.CurrencyResponse
import com.marazmone.crypton.domain.repository.CurrencyRepository

class CurrencyGetAllUseCase(
    private val repository: CurrencyRepository,
) {

    suspend fun execute(): List<CurrencyResponse> = repository.getAll()
}