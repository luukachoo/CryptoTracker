package com.luukachoo.cryptotracker.crypto.data.mappers

import com.luukachoo.cryptotracker.crypto.data.networking.dto.CoinDto
import com.luukachoo.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.luukachoo.cryptotracker.crypto.domain.Coin
import com.luukachoo.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr ?: 0.0
)

fun CoinPriceDto.toCoinPrice() = CoinPrice(
    priceUsd = priceUsd,
    dateTime = Instant
        .ofEpochMilli(time)
        .atZone(ZoneId.systemDefault())
)