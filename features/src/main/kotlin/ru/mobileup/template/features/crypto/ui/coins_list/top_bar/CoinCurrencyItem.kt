package ru.mobileup.template.features.crypto.ui.coins_list.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.theme.coins_theme.CoinTheme
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.features.crypto.domain.CoinCurrency

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinCurrencyItem(
    currency: CoinCurrency,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .heightIn(max = 32.dp)
            .widthIn(min = 89.dp),
        enabled = onClick != null,
        onClick = { onClick?.invoke() },
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        color = when (isSelected) {
            true -> CoinTheme.colors.chip.chipSelected
            else -> CoinTheme.colors.chip.chipUnselected
        }
    ) {
        Text(
            text = currency.value.uppercase(),
            style = MaterialTheme.typography.body1,
            color = when (isSelected) {
                true -> CoinTheme.colors.text.chipSelectedText
                else -> CoinTheme.colors.text.chipUnselectedText
            },
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
fun CoinCurrencyItemPreview() {
    var isSelected by remember { mutableStateOf(false) }

    AppTheme {
        CoinCurrencyItem(
            currency = CoinCurrency("usd"),
            isSelected = isSelected,
            onClick = { isSelected = !isSelected }
        )
    }
}