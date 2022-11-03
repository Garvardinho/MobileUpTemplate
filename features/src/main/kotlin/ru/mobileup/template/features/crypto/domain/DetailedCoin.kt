package ru.mobileup.template.features.crypto.domain

data class DetailedCoin(
    val id: CoinId,
    val name: String,
    val imageUrl: String,
    val description: String,
    val categories: List<String>
)