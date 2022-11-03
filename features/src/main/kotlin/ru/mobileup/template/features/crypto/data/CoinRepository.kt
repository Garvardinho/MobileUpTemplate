package ru.mobileup.template.features.crypto.data

import me.aartikov.replica.keyed.KeyedPhysicalReplica
import ru.mobileup.template.features.crypto.domain.Coin
import ru.mobileup.template.features.crypto.domain.CoinId
import ru.mobileup.template.features.crypto.domain.Currency
import ru.mobileup.template.features.crypto.domain.DetailedCoin

interface CoinRepository {

    val coinsByCurrencyReplica: KeyedPhysicalReplica<Currency, List<Coin>>

    val coinDetailsByIdReplica: KeyedPhysicalReplica<CoinId, DetailedCoin>
}