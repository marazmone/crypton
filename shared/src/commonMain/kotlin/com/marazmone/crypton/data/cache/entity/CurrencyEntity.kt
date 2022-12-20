package com.marazmone.crypton.data.cache.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CurrencyEntity : RealmObject {

    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var symbol: String = ""
    var maxSupply: Float = 0f
    var circulatingSupply: Float = 0f
    var totalSupply: Float = 0f
    var cmcRank: Int = 0
    var lastUpdated: String = ""
    var price: Float = 0f
    var percentChange1h: Float = 0f
    var percentChange24h: Float = 0f
    var percentChange7d: Float = 0f
    var marketCap: Float = 0f
    var imageUrl: String = ""
    var high24h: Float = 0f
    var low24h: Float = 0f
    var ath: Float = 0f
    var athChangePercentage: Float = 0f
    var athDate: Long = 0
    var atl: Float = 0f
    var atlChangePercentage: Float = 0f
    var atlDate: Long = 0
    var totalValue: Float = 0f
    var isFavorite: Boolean = false
}
