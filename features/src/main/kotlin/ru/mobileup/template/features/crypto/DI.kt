package ru.mobileup.template.features.crypto

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.withKey
import org.koin.core.component.get
import org.koin.dsl.module
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.network.NetworkApiFactory
import ru.mobileup.template.features.crypto.data.CoinApi
import ru.mobileup.template.features.crypto.data.CoinRepository
import ru.mobileup.template.features.crypto.data.CoinRepositoryImpl
import ru.mobileup.template.features.crypto.domain.CoinId
import ru.mobileup.template.features.crypto.ui.CoinsComponent
import ru.mobileup.template.features.crypto.ui.RealCoinsComponent
import ru.mobileup.template.features.crypto.ui.details.CoinDetailsComponent
import ru.mobileup.template.features.crypto.ui.details.RealCoinDetailsComponent
import ru.mobileup.template.features.crypto.ui.coins_list.CoinsListComponent
import ru.mobileup.template.features.crypto.ui.coins_list.RealCoinsListComponent

val coinsModule = module {
    single<CoinApi> { get<NetworkApiFactory>().createUnauthorizedApi() }
    single<CoinRepository> { CoinRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createCoinsComponent(
    componentContext: ComponentContext
): CoinsComponent {
    return RealCoinsComponent(componentContext, get())
}

fun ComponentFactory.createCoinListComponent(
    componentContext: ComponentContext,
    onOutput: (CoinsListComponent.Output) -> Unit
): CoinsListComponent {
    val coinsByCurrencyReplica = get<CoinRepository>().coinsByCurrencyReplica
    return RealCoinsListComponent(componentContext, onOutput, coinsByCurrencyReplica, get())
}

fun ComponentFactory.createCoinDetailsComponent(
    componentContext: ComponentContext,
    coinId: CoinId
): CoinDetailsComponent {
    val coinDetailsByIdReplica = get<CoinRepository>().coinDetailsByIdReplica.withKey(coinId)
    return RealCoinDetailsComponent(componentContext, coinDetailsByIdReplica, get())
}