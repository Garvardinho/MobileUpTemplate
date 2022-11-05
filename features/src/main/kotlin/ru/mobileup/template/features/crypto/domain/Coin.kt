package ru.mobileup.template.features.crypto.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class CoinCurrency(val value: String) : Parcelable

@Parcelize
@JvmInline
value class CoinId(val value: String) : Parcelable

data class Coin(
    val id: CoinId,
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val currentPrice: Double,
    val priceChangePercentage: String
)