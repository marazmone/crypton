package com.marazmone.crypton.domain.mapper.currency

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.data.mapper.base.Mapper
import com.marazmone.crypton.domain.model.currency.CurrencyDetail

class CurrencyEntityToDetailMapper : Mapper<CurrencyEntity, CurrencyDetail> {

    override fun map(source: CurrencyEntity): CurrencyDetail =
        CurrencyDetail(
            id = source.id,
            name = source.name,
            isFavorite = source.isFavorite,
            percentChange24H = source.percentChange24h,
            percentChange1H = source.percentChange1h,
            percentChange7D = source.percentChange7d,
            _price = source.price,
            symbol = source.symbol,
            rank = source.cmcRank,
            imageUrl = source.imageUrl,
            _marketCap = source.marketCap,
            _supplyCirculating = source.circulatingSupply,
            _supplyMax = source.maxSupply,
            _supplyTotal = source.totalSupply,
            max24H = source.high24h,
            min24H = source.low24h,
            tradingVolume = source.totalValue,
            athDate = source.athDate,
            athPrice = source.ath,
            athPercentChange = source.athChangePercentage,
            atlPrice = source.atl,
            atlPercentChange = source.atlChangePercentage,
            atlDate = source.atlDate,
        )
}
