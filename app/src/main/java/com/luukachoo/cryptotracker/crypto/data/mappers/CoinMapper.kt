package com.luukachoo.cryptotracker.crypto.data.mappers

import com.luukachoo.cryptotracker.crypto.data.networking.dto.CoinDto
import com.luukachoo.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr
)