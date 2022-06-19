package com.marazmone.crypton.data.datasource

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.utils.insertOrUpdate
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyCacheDataSourceImpl(
    private val realm: Realm,
) : CurrencyCacheDataSource {

    override suspend fun save(entity: CurrencyEntity) {
        realm.write { insertOrUpdate(entity) }
    }

    override suspend fun save(entity: List<CurrencyEntity>) {
        realm.write { entity.forEach(::insertOrUpdate) }
    }

    override suspend fun getById(id: String): CurrencyEntity? =
        realm.query<CurrencyEntity>("id == $0", id).find().firstOrNull()


    override suspend fun getAllByLimit(limit: Int): List<CurrencyEntity> =
        realm.query<CurrencyEntity>("limit == $0", limit).find().sortedBy { it.cmcRank }

    override suspend fun updateFavorite(id: String, isFavorite: Boolean) {
        val currentEntity = getById(id)
        requireNotNull(currentEntity) { "updateFavorite: currentEntity is null" }
        currentEntity.isFavorite = isFavorite
        save(currentEntity)
    }

    override suspend fun getAllFavorite(): List<CurrencyEntity> =
        realm.query<CurrencyEntity>("isFavorite == $0", true).find().sortedBy { it.cmcRank }

    override fun observeById(id: Int): Flow<CurrencyEntity?> =
        realm.query<CurrencyEntity>("id == $0", id).find().asFlow().map { change ->
            when (change) {
                is InitialResults, is UpdatedResults -> change.list.firstOrNull()
            }
        }

    override fun observeAllByLimit(limit: Int): Flow<List<CurrencyEntity>> =
        realm.query<CurrencyEntity>("limit == $0", limit).find().asFlow().map { change ->
            when (change) {
                is InitialResults, is UpdatedResults -> change.list.sortedBy { it.cmcRank }
            }
        }
}