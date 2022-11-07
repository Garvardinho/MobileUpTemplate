package ru.mobileup.template.features.crypto.data.dto

import ru.mobileup.template.features.crypto.domain.CoinCurrency
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

fun getFormattedCurrency(currency: CoinCurrency, price: Double): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance()
    currencyFormatter.maximumFractionDigits = 2

    if (currency.value == "eur")
        currencyFormatter.currency = Currency.getInstance("EUR")
    else
        currencyFormatter.currency = Currency.getInstance("USD")

    return currencyFormatter.format(price)
}

fun getFormattedPercentage(percentage: Double): String {
    val formatted = (percentage * 100).roundToInt().toDouble() / 100
    return if (formatted < 0)
        "$formatted%"
    else
        "+$formatted%"
}