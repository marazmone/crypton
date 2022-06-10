package com.marazmone.crypton.domain.repository

import com.marazmone.crypton.data.remote.response.CurrencyResponse

interface CurrencyRepository {

    suspend fun getAll(): List<CurrencyResponse>
}