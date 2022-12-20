package com.marazmone.crypton.domain.mapper.currency

import com.marazmone.crypton.data.cache.entity.CurrencyEntity
import com.marazmone.crypton.data.mapper.base.Mapper
import com.marazmone.crypton.domain.model.currency.CurrencyListItem
import com.marazmone.crypton.utils.orZero

class CurrencyEntityToListItemMapper : Mapper<CurrencyEntity, CurrencyListItem> {

    override fun map(source: CurrencyEntity): CurrencyListItem =
        CurrencyListItem(
            id = source.id,
            rank = source.cmcRank.orZero,
            symbol = source.symbol.uppercase(),
            name = source.name,
            percentChange24H = source.percentChange24h.orZero,
            _price = source.price.orZero,
            _mCap = source.marketCap.orZero,
            imageUrl = source.imageUrl,
            isFavorite = source.isFavorite
        )
}
