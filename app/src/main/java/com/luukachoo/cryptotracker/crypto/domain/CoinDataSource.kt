package com.luukachoo.cryptotracker.crypto.domain

import com.luukachoo.cryptotracker.core.domain.util.NetworkError
import com.luukachoo.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}