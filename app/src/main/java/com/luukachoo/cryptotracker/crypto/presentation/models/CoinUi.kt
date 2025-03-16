package com.luukachoo.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes
import com.luukachoo.cryptotracker.core.presentation.util.getDrawableIdForCoin
import com.luukachoo.cryptotracker.crypto.domain.Coin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    @DrawableRes val iconRes: Int,
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Coin.toCoinUi() = CoinUi(
    iconRes = getDrawableIdForCoin(symbol),
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd.toDisplayableNumber(),
    priceUsd = priceUsd.toDisplayableNumber(),
    changePercent24Hr = changePercent24Hr.toDisplayableNumber(),

    )

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}