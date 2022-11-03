package ru.mobileup.template.features.crypto.ui.coins_list

import me.aartikov.replica.single.Loadable
import ru.mobileup.template.features.crypto.domain.Coin
import ru.mobileup.template.features.crypto.domain.CoinId
import ru.mobileup.template.features.crypto.domain.Currency

interface CoinsListComponent {

    val currencies: List<Currency>

    val selectedCurrency: Currency

    val coinsState: Loadable<List<Coin>>

    fun onCurrencyClick(currency: Currency)

    fun onCoinClick(coinId: CoinId)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class CoinDetailsRequested(val coinId: CoinId) : Output
    }
}