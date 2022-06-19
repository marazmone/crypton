package com.marazmone.crypton.di

import com.marazmone.crypton.data.datasource.CurrencyCacheDataSource
import com.marazmone.crypton.data.datasource.CurrencyCacheDataSourceImpl
import com.marazmone.crypton.data.datasource.CurrencyRemoteDataSource
import com.marazmone.crypton.data.datasource.CurrencyRemoteDataSourceImpl
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val datasourceModule = module {
    singleOf(::CurrencyRemoteDataSourceImpl) { bind<CurrencyRemoteDataSource>() }
    singleOf(::CurrencyCacheDataSourceImpl) { bind<CurrencyCacheDataSource>() }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(tag = "HTTPClient", message = message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}
