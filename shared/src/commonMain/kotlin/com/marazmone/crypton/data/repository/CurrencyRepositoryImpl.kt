package com.marazmone.crypton.data.repository

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.data.datasource.CurrencyCacheDataSource
import com.marazmone.crypton.data.datasource.CurrencyRemoteDataSource
import com.marazmone.crypton.data.mapper.base.Mapper
import com.marazmone.crypton.data.remote.response.CurrencyResponse
import com.marazmone.crypton.domain.model.CurrencyListItem
import com.marazmone.crypton.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyRepositoryImpl(
    private val remote: CurrencyRemoteDataSource,
    private val cache: CurrencyCacheDataSource,
    private val responseToEntityMapper: Mapper<CurrencyResponse, CurrencyEntity>,
    private val entityToListItemMapper: Mapper<CurrencyEntity, CurrencyListItem>,
) : CurrencyRepository {

    override suspend fun getAll(): List<CurrencyListItem> {
        val remote = remote.getAll()
        val entities = responseToEntityMapper.listAsync(remote)
        cache.save(entities)
        return entityToListItemMapper.listAsync(entities)
    }

    override fun observeAll(): Flow<List<CurrencyListItem>> =
        cache.observeAll().map { entityToListItemMapper.listAsync(it) }
}