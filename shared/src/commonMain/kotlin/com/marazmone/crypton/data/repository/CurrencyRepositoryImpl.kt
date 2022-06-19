package com.marazmone.crypton.data.repository

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.data.datasource.CurrencyRemoteDataSource
import com.marazmone.crypton.data.mapper.base.Mapper
import com.marazmone.crypton.data.remote.response.CurrencyResponse
import com.marazmone.crypton.domain.model.CurrencyListItem
import com.marazmone.crypton.domain.repository.CurrencyRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CurrencyRepositoryImpl(
    private val remote: CurrencyRemoteDataSource,
    private val responseToEntityMapper: Mapper<CurrencyResponse, CurrencyEntity>,
    private val entityToListItemMapper: Mapper<CurrencyEntity, CurrencyListItem>,
) : CurrencyRepository {

    override suspend fun getAll(): List<CurrencyListItem> = CurrencyResponse.list(remote.getAll())
}