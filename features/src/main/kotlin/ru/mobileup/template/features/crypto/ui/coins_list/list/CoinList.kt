package ru.mobileup.template.features.crypto.ui.coins_list.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mobileup.template.features.crypto.domain.Coin
import ru.mobileup.template.features.crypto.domain.CoinId
import ru.mobileup.template.features.crypto.domain.Currency

@Composable
fun CoinList(
    coins: List<Coin>,
    onCoinClick: (CoinId) -> Unit,
    currency: Currency,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier
            .fillMaxSize()
    ) {
        items(
            items = coins,
            key = { it.id }
        ) { coin ->
            CoinItem(
                coin = coin,
                currency = currency,
                onClick = { onCoinClick(coin.id) },
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
