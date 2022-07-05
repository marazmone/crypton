package com.marazmone.crypton.domain.usecase

import com.marazmone.crypton.domain.repository.CurrencyRepository

class CurrencySetFavoriteByIdUseCase(
    private val repository: CurrencyRepository,
) {

    suspend fun execute(id: String, isFavorite: Boolean) = repository.updateFavorite(id, isFavorite)
}