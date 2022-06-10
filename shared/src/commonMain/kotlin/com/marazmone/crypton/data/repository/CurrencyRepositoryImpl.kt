package com.marazmone.crypton.data.repository

import com.marazmone.crypton.data.datasource.CurrencyRemoteDataSource
import com.marazmone.crypton.data.remote.response.CurrencyResponse
import com.marazmone.crypton.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    private val remote: CurrencyRemoteDataSource,
) : CurrencyRepository {

    override suspend fun getAll(): List<CurrencyResponse> = remote.getAll()
}