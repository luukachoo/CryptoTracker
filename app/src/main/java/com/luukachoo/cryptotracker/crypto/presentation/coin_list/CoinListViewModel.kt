package com.luukachoo.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luukachoo.cryptotracker.core.domain.util.onError
import com.luukachoo.cryptotracker.core.domain.util.onSuccess
import com.luukachoo.cryptotracker.crypto.domain.CoinDataSource
import com.luukachoo.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CoinListState()
        )

    fun onAction(action: CoinListAction) = when(action) {
        is CoinListAction.OnCoinClick -> Unit
    }

    private fun loadCoins() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        coinDataSource.getCoins()
            .onSuccess { coins ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        coins = coins.map { it.toCoinUi() }
                    )
                }
            }
            .onError {
                _state.update { it.copy(isLoading = false) }
            }
    }
}