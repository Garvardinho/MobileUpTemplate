package ru.mobileup.template.features.crypto.ui.coins_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.aartikov.replica.single.Loadable
import ru.mobileup.template.core.theme.coins_theme.CoinTheme
import ru.mobileup.template.core.widget.EmptyPlaceholder
import ru.mobileup.template.core.widget.RefreshingProgress
import ru.mobileup.template.core.widget.SwipeRefreshLceWidget
import ru.mobileup.template.features.R
import ru.mobileup.template.features.crypto.domain.Coin
import ru.mobileup.template.features.crypto.domain.CoinId
import ru.mobileup.template.features.crypto.domain.Currency
import ru.mobileup.template.features.crypto.ui.coins_list.list.CoinList
import ru.mobileup.template.features.crypto.ui.coins_list.top_bar.CoinCurrencyBar

@Composable
fun CoinsListUi(
    component: CoinsListComponent,
    modifier: Modifier = Modifier
) {
    Surface(
        color = CoinTheme.colors.primary,
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CoinCurrencyBar(
                currencies = component.currencies,
                selectedCurrency = component.selectedCurrency,
                onCurrencyClick = component::onCurrencyClick
            )
            SwipeRefreshLceWidget(
                state = component.coinsState,
                onRefresh = component::onRefresh,
                onRetryClick = component::onRetryClick
            ) { coins, refreshing ->
                if (coins.isNotEmpty()) {
                    CoinList(
                        coins = coins,
                        onCoinClick = component::onCoinClick,
                        currency = component.selectedCurrency
                    )
                } else {
                    EmptyPlaceholder(description = stringResource(R.string.coins_not_found))
                }
                RefreshingProgress(refreshing)
            }
        }
    }
}

@Preview
@Composable
fun CoinsListUiPreview() {
    CoinTheme {
        CoinsListUi(FakeCoinsListComponent())
    }
}

class FakeCoinsListComponent : CoinsListComponent {

    override val currencies: List<Currency> = listOf(
        Currency("usd"),
        Currency("eur")
    )

    override val selectedCurrency: Currency = currencies[0]

    override val coinsState: Loadable<List<Coin>> = Loadable(
        loading = true,
        data = listOf(
            Coin(
                id = CoinId("bitcoin"),
                symbol = "btc",
                name = "Bitcoin",
                imageUrl = "",
                currentPrice = 28600.74,
                priceChangePercentage = "4.05"
            ),
            Coin(
                id = CoinId("ethereum"),
                symbol = "eth",
                name = "Ethereum",
                imageUrl = "",
                currentPrice = 2600.74,
                priceChangePercentage = "-2.14"
            ),
            Coin(
                id = CoinId("binance"),
                symbol = "bnb",
                name = "Binance",
                imageUrl = "",
                currentPrice = 432.69,
                priceChangePercentage = "14.05"
            ),
            Coin(
                id = CoinId("ethereum2"),
                symbol = "eth",
                name = "Ethereum",
                imageUrl = "",
                currentPrice = 2600.74,
                priceChangePercentage = "-2.14"
            ),
            Coin(
                id = CoinId("binance2"),
                symbol = "bnb",
                name = "Binance",
                imageUrl = "",
                currentPrice = 432.69,
                priceChangePercentage = "14.05"
            )
        )
    )

    override fun onCurrencyClick(currency: Currency) = Unit

    override fun onCoinClick(coinId: CoinId) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}
