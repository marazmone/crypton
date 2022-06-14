package com.marazmone.crypton.domain.repository

import com.marazmone.crypton.domain.model.CurrencyListItem

interface CurrencyRepository {

    suspend fun getAll(): List<CurrencyListItem>
}