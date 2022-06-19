package com.marazmone.crypton.domain.repository

import com.marazmone.crypton.domain.model.CurrencyListItem
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getAll(): List<CurrencyListItem>

    fun observeAll(): Flow<List<CurrencyListItem>>
}