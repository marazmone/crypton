package com.marazmone.crypton.data.datasource

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.data.remote.response.CurrencyResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyCacheDataSource {

    suspend fun save(entity: CurrencyEntity)

    suspend fun save(entities: List<CurrencyEntity>)

    suspend fun getById(id: String): CurrencyEntity?

    suspend fun getAll(): List<CurrencyEntity>

    suspend fun updateFavorite(id: String, isFavorite: Boolean)

    suspend fun getAllFavorite(): List<CurrencyEntity>

    fun observeById(id: Int): Flow<CurrencyEntity?>

    fun observeAll(): Flow<List<CurrencyEntity>>

    fun observeAllFavorite(): Flow<List<CurrencyEntity>>
}

interface CurrencyRemoteDataSource {

    suspend fun getAll(
        vsCurrency: String = "USD",
        priceChangePercentage: String = "1h,7d",
        ids: String? = null,
    ): List<CurrencyResponse>
}