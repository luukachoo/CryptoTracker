package com.luukachoo.cryptotracker.crypto.presentation.coin_list

import com.luukachoo.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val message: NetworkError) : CoinListEvent
}