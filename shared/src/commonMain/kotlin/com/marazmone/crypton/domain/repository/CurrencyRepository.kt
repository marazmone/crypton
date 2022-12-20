package com.marazmone.crypton.domain.repository

import com.marazmone.crypton.domain.model.currency.CurrencyDetail
import com.marazmone.crypton.domain.model.currency.CurrencyListItem
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getAll(): List<CurrencyListItem>

    suspend fun getAllFavorite(): List<CurrencyListItem>

    suspend fun getCurrencyById(id: String): CurrencyDetail?

    suspend fun updateFavorite(id: String, isFavorite: Boolean)

    suspend fun updateByIds(ids: List<String>): List<CurrencyListItem>

    fun observeAll(): Flow<List<CurrencyListItem>>

    fun observeAllFavorite(): Flow<List<CurrencyListItem>>
}
