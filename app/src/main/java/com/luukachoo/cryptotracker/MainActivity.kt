package com.luukachoo.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luukachoo.cryptotracker.core.presentation.util.ObserveAsEvents
import com.luukachoo.cryptotracker.core.presentation.util.toString
import com.luukachoo.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.luukachoo.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.luukachoo.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.luukachoo.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.luukachoo.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val context = LocalContext.current
                    ObserveAsEvents(events = viewModel.events) { event ->
                        when (event) {
                            is CoinListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    event.message.toString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    when {
                        state.selectedCoin != null -> {
                            CoinDetailScreen(
                                modifier = Modifier.padding(innerPadding),
                                state = state
                            )
                        }

                        else -> {
                            CoinListScreen(
                                modifier = Modifier.padding(innerPadding),
                                state = state,
                                onAction = viewModel::onAction
                            )
                        }
                    }
                }
            }
        }
    }
}