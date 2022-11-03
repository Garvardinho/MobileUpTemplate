package ru.mobileup.template.features.crypto.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.aartikov.replica.single.Loadable
import ru.mobileup.template.core.theme.coins_theme.CoinTheme
import ru.mobileup.template.core.widget.RefreshingProgress
import ru.mobileup.template.core.widget.SwipeRefreshLceWidget
import ru.mobileup.template.features.R
import ru.mobileup.template.features.crypto.domain.CoinId
import ru.mobileup.template.features.crypto.domain.DetailedCoin

@Composable
fun CoinDetailsUi(
    component: CoinDetailsComponent, modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(), color = MaterialTheme.colors.primary
    ) {
        SwipeRefreshLceWidget(
            state = component.coinState,
            onRefresh = component::onRefresh,
            onRetryClick = component::onRetryClick
        ) { coin, refreshing ->
            CoinDetailsUiContent(coin)
            RefreshingProgress(refreshing, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
fun CoinDetailsUiContent(
    coin: DetailedCoin, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            contentDescription = null,
            model = ImageRequest.Builder(LocalContext.current)
                .data(coin.imageUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.4.dp),
            placeholder = painterResource(id = R.mipmap.ic_bitcoin_foreground)
        )

        Text(
            text = stringResource(id = R.string.description_title),
            style = CoinTheme.typography.text.semiBold,
            color = CoinTheme.colors.text.detailsText,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = HtmlCompat
                .fromHtml(coin.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                .toString(),
            style = CoinTheme.typography.text.normal,
            color = CoinTheme.colors.text.detailsText
        )

        Text(
            text = stringResource(id = R.string.categories_title),
            style = CoinTheme.typography.text.semiBold,
            color = CoinTheme.colors.text.detailsText,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = coin.categories.joinToString(),
            style = CoinTheme.typography.text.normal,
            color = CoinTheme.colors.text.detailsText,
            modifier = Modifier.padding(bottom = 34.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CoinDetailsUiPreview() {
    CoinTheme {
        CoinDetailsUi(component = FakeCoinDetailsComponent())
    }
}

class FakeCoinDetailsComponent : CoinDetailsComponent {

    override val coinState = Loadable(
        loading = true,
        data = DetailedCoin(
            id = CoinId("bitcoin"),
            name = "Bitcoin",
            imageUrl = "",
            description = "Bitcoin is the first successful internet money based on peer-to-peer technology; whereby no central bank or authority is involved in the transaction and production of the Bitcoin currency. It was created by an anonymous individual/group under the name, Satoshi Nakamoto. The source code is available publicly as an open source project, anybody can look at it and be part of the developmental process.\r\n\r\nBitcoin is changing the way we see money as we speak. The idea was to produce a means of exchange, independent of any central authority, that could be transferred electronically in a secure, verifiable and immutable way. It is a decentralized peer-to-peer internet currency making mobile payment easy, very low transaction fees, protects your identity, and it works anywhere all the time with no central authority and banks.\r\n\r\nBitcoin is designed to have only 21 million BTC ever created, thus making it a deflationary currency. Bitcoin uses the <a href=\\\"https://www.coingecko.com/en?hashing_algorithm=SHA-256\\\">SHA-256</a> hashing algorithm with an average transaction confirmation time of 10 minutes. Miners today are mining Bitcoin using ASIC chip dedicated to only mining Bitcoin, and the hash rate has shot up to peta hashes.\r\n\r\nBeing the first successful online cryptography currency, Bitcoin has inspired other alternative currencies such as <a href=\\\"https://www.coingecko.com/en/coins/litecoin\\\">Litecoin</a>, <a href=\\\"https://www.coingecko.com/en/coins/peercoin\\\">Peercoin</a>, <a href=\\\"https://www.coingecko.com/en/coins/primecoin\\\">Primecoin</a>, and so on.\r\n\r\nThe cryptocurrency then took off with the innovation of the turing-complete smart contract by <a href=\\\"https://www.coingecko.com/en/coins/ethereum\\\">Ethereum</a> which led to the development of other amazing projects such as <a href=\\\"https://www.coingecko.com/en/coins/eos\\\">EOS</a>, <a href=\\\"https://www.coingecko.com/en/coins/tron\\\">Tron</a>, and even crypto-collectibles such as <a href=\\\"https://www.coingecko.com/buzz/ethereum-still-king-dapps-cryptokitties-need-1-billion-on-eos\\\">CryptoKitties</a>.",
            categories = listOf(
                "Smart Contract Platform",
                "Ethereum Ecosystems"
            )
        )
    )

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}
