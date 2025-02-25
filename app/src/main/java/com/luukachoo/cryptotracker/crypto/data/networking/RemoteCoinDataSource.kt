package com.luukachoo.cryptotracker.crypto.data.networking

import com.luukachoo.cryptotracker.core.data.networking.constructUrl
import com.luukachoo.cryptotracker.core.data.networking.safeCall
import com.luukachoo.cryptotracker.core.domain.util.NetworkError
import com.luukachoo.cryptotracker.core.domain.util.Result
import com.luukachoo.cryptotracker.core.domain.util.map
import com.luukachoo.cryptotracker.crypto.data.mappers.toCoin
import com.luukachoo.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.luukachoo.cryptotracker.crypto.domain.Coin
import com.luukachoo.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { coinResponseDto ->
            coinResponseDto.data.map { it.toCoin() }
        }
    }
}