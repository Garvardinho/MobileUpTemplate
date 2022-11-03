package ru.mobileup.template.features.crypto.ui.coins_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed.keepPreviousData
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.crypto.domain.Coin
import ru.mobileup.template.features.crypto.domain.CoinId
import ru.mobileup.template.features.crypto.domain.Currency

class RealCoinsListComponent(
    componentContext: ComponentContext,
    private val onOutput: (CoinsListComponent.Output) -> Unit,
    private val coinByCurrencyReplica: KeyedReplica<Currency, List<Coin>>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, CoinsListComponent {

    override val currencies = listOf(
        Currency("usd"),
        Currency("eur")
    )
    override var selectedCurrency by mutableStateOf(currencies[0])
        private set

    override val coinsState by coinByCurrencyReplica
        .keepPreviousData()
        .observe(
            lifecycle,
            errorHandler,
            key = { selectedCurrency }
        )

    override fun onCurrencyClick(currency: Currency) {
        selectedCurrency = currency
    }

    override fun onRetryClick() {
        coinByCurrencyReplica.refresh(selectedCurrency)
    }

    override fun onRefresh() {
        coinByCurrencyReplica.refresh(selectedCurrency)
    }

    override fun onCoinClick(coinId: CoinId) {
        onOutput(CoinsListComponent.Output.CoinDetailsRequested(coinId))
    }
}