package com.marazmone.crypton.data.repository

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.data.datasource.CurrencyCacheDataSource
import com.marazmone.crypton.data.datasource.CurrencyRemoteDataSource
import com.marazmone.crypton.data.mapper.base.Mapper
import com.marazmone.crypton.data.remote.response.CurrencyResponse
import com.marazmone.crypton.domain.model.currency.CurrencyDetail
import com.marazmone.crypton.domain.model.currency.CurrencyListItem
import com.marazmone.crypton.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyRepositoryImpl(
    private val remote: CurrencyRemoteDataSource,
    private val cache: CurrencyCacheDataSource,
    private val responseToEntityMapper: Mapper<CurrencyResponse, CurrencyEntity>,
    private val entityToListItemMapper: Mapper<CurrencyEntity, CurrencyListItem>,
    private val entityToDetailMapper: Mapper<CurrencyEntity, CurrencyDetail>,
) : CurrencyRepository {

    override suspend fun getAll(): List<CurrencyListItem> {
        val remote = remote.getAll()
        val entities = responseToEntityMapper.listAsync(remote)
        cache.save(entities)
        return entityToListItemMapper.listAsync(entities)
    }

    override suspend fun getAllFavorite(): List<CurrencyListItem> {
        val entities = cache.getAllFavorite()
        return entityToListItemMapper.listAsync(entities)
    }

    override suspend fun getCurrencyById(id: String): CurrencyDetail? =
        cache.getById(id)?.let { entityToDetailMapper.mapAsync(it) }

    override suspend fun updateFavorite(id: String, isFavorite: Boolean) =
        cache.updateFavorite(id, isFavorite)

    override suspend fun updateByIds(ids: List<String>): List<CurrencyListItem> {
        val remote = remote.getAll(ids = ids.joinToString(","))
        val entities = responseToEntityMapper.listAsync(remote)
        cache.save(entities)
        return entityToListItemMapper.listAsync(entities)
    }

    override fun observeAll(): Flow<List<CurrencyListItem>> =
        cache.observeAll().map { entityToListItemMapper.listAsync(it) }

    override fun observeAllFavorite(): Flow<List<CurrencyListItem>> =
        cache.observeAllFavorite().map { entityToListItemMapper.listAsync(it) }
}
