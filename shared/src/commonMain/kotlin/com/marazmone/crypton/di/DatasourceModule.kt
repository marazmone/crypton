package com.marazmone.crypton.di

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.data.datasource.CurrencyCacheDataSource
import com.marazmone.crypton.data.datasource.CurrencyCacheDataSourceImpl
import com.marazmone.crypton.data.datasource.CurrencyRemoteDataSource
import com.marazmone.crypton.data.datasource.CurrencyRemoteDataSourceImpl
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private const val DB_VERSION = 1L
private const val HTTP_LOG_TAG = "HTTPClient"

val datasourceModule = module {
    singleOf(::CurrencyRemoteDataSourceImpl) { bind<CurrencyRemoteDataSource>() }
    singleOf(::CurrencyCacheDataSourceImpl) { bind<CurrencyCacheDataSource>() }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(tag = HTTP_LOG_TAG, message = message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    single {
        val builder = RealmConfiguration.Builder(
            schema = setOf(
                CurrencyEntity::class,
            )
        )
        builder.schemaVersion(DB_VERSION)
        builder.deleteRealmIfMigrationNeeded()
        Realm.open(builder.build())
    }
}
