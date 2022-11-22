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

    override suspend fun save(entities: List<CurrencyEntity>) {
        realm.write {
            entities.forEach { newEntity ->
                val currentEntityById = query<CurrencyEntity>("id == $0", newEntity.id)
                    .find()
                    .firstOrNull()
                val currentEntityByRank = query<CurrencyEntity>("cmcRank == $0", newEntity.cmcRank)
                    .find()
                    .firstOrNull()
                if (currentEntityById != null) newEntity.isFavorite = currentEntityById.isFavorite
                if (currentEntityByRank != null) delete(currentEntityByRank)
                insertOrUpdate(newEntity)
            }
        }
    }

    override suspend fun getById(id: String): CurrencyEntity? =
        realm.query<CurrencyEntity>("id == $0", id).find().firstOrNull()


    override suspend fun getAll(): List<CurrencyEntity> =
        realm.query<CurrencyEntity>().find().sortedBy { it.cmcRank }

    override suspend fun updateFavorite(id: String, isFavorite: Boolean) {
        realm.write {
            val currentEntity = query<CurrencyEntity>("id == $0", id).find().firstOrNull()
            requireNotNull(currentEntity) { "updateFavorite: currentEntity is null" }
            currentEntity.isFavorite = isFavorite
        }
    }

    override suspend fun getAllFavorite(): List<CurrencyEntity> =
        realm.query<CurrencyEntity>("isFavorite == $0", true).find().sortedBy { it.cmcRank }

    override fun observeById(id: Int): Flow<CurrencyEntity?> =
        realm.query<CurrencyEntity>("id == $0", id).find().asFlow().map { change ->
            when (change) {
                is InitialResults, is UpdatedResults -> change.list.firstOrNull()
            }
        }

    override fun observeAll(): Flow<List<CurrencyEntity>> =
        realm.query<CurrencyEntity>().find().asFlow().map { change ->
            when (change) {
                is InitialResults, is UpdatedResults -> change.list.sortedBy { it.cmcRank }
            }
        }

    override fun observeAllFavorite(): Flow<List<CurrencyEntity>> =
        realm.query<CurrencyEntity>("isFavorite == $0", true).find().asFlow().map { change ->
            when (change) {
                is InitialResults, is UpdatedResults -> change.list.sortedBy { it.cmcRank }
            }
        }
}