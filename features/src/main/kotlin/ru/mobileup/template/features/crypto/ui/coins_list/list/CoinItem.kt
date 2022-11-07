package ru.mobileup.template.features.crypto.ui.coins_list.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.mobileup.template.core.theme.coins_theme.CoinTheme
import ru.mobileup.template.features.R
import ru.mobileup.template.features.crypto.domain.Coin
import ru.mobileup.template.features.crypto.domain.CoinCurrency
import java.text.NumberFormat
import java.util.*

@Composable
fun CoinItem(
    coin: Coin,
    currency: CoinCurrency,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            contentDescription = null,
            model = ImageRequest.Builder(LocalContext.current)
                .data(coin.imageUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically),
            placeholder = painterResource(R.mipmap.ic_placeholder_round)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = coin.name,
                style = CoinTheme.typography.coin.medium,
                color = CoinTheme.colors.coin.coinName
            )
            Text(
                text = coin.symbol.uppercase(),
                style = CoinTheme.typography.coin.normal,
                color = CoinTheme.colors.coin.coinSymbol
            )
        }
        val currencyFormatter = NumberFormat.getCurrencyInstance()
        currencyFormatter.maximumFractionDigits = 2

        if (currency.value == "eur")
            currencyFormatter.currency = Currency.getInstance("EUR")
        else
            currencyFormatter.currency = Currency.getInstance("USD")

        val percentFormatter = NumberFormat.getPercentInstance()
        percentFormatter.maximumFractionDigits = 2

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = currencyFormatter.format(coin.currentPrice),
                style = CoinTheme.typography.coin.semiBold,
                color = CoinTheme.colors.text.title
            )
            Text(
                text = percentFormatter.format(coin.priceChangePercentage.toDouble()),
                style = CoinTheme.typography.coin.normal,
                color = if (coin.priceChangePercentage.toDouble() < 0)
                    CoinTheme.colors.text.percentBelowZero
                else
                    CoinTheme.colors.text.percentAboveZero
            )
        }
    }
}
