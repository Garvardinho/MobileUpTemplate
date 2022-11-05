package ru.mobileup.template.features.crypto.ui.coins_list.top_bar

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.theme.coins_theme.CoinTheme
import ru.mobileup.template.core.widget.BottomShadow
import ru.mobileup.template.core.widget.TopShadow
import ru.mobileup.template.features.R
import ru.mobileup.template.features.crypto.domain.CoinCurrency
import ru.mobileup.template.features.crypto.ui.coins_list.list.CoinCurrencyItem

@Composable
fun CoinCurrencyBar(
    currencies: List<CoinCurrency>,
    selectedCurrency: CoinCurrency,
    onCurrencyClick: (CoinCurrency) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            TopShadow()
            Text(
                text = stringResource(R.string.coin_home_bar_title),
                style = CoinTheme.typography.topBar.semiBold,
                color = CoinTheme.colors.text.title,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 32.dp
                )
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 16.dp, bottom = 13.dp)
            ) {
                currencies.forEach { currency ->
                    CoinCurrencyItem(
                        currency = currency,
                        isSelected = currency == selectedCurrency,
                        onClick = { onCurrencyClick(currency) }
                    )
                }
            }
            BottomShadow()
        }
    }
}
