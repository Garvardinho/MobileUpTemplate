package ru.mobileup.template.features.crypto.ui

import android.os.Parcelable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import kotlinx.parcelize.Parcelize
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.utils.toComposeState
import ru.mobileup.template.features.crypto.createCoinDetailsComponent
import ru.mobileup.template.features.crypto.createCoinListComponent
import ru.mobileup.template.features.crypto.domain.CoinId
import ru.mobileup.template.features.crypto.ui.coins_list.CoinsListComponent

class RealCoinsComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, CoinsComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: ChildStack<*, CoinsComponent.Child> by childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild
    ).toComposeState(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): CoinsComponent.Child = when (config) {
        is ChildConfig.List -> {
            CoinsComponent.Child.List(
                componentFactory.createCoinListComponent(
                    componentContext,
                    ::onCoinListOutput
                )
            )
        }

        is ChildConfig.Details -> {
            CoinsComponent.Child.Details(
                componentFactory.createCoinDetailsComponent(
                    componentContext,
                    coinId = config.coinId
                )
            )
        }
    }

    private fun onCoinListOutput(output: CoinsListComponent.Output) {
        when (output) {
            is CoinsListComponent.Output.CoinDetailsRequested -> {
                navigation.push(ChildConfig.Details(output.coinId))
            }
        }
    }

    private sealed interface ChildConfig : Parcelable {

        @Parcelize
        object List : ChildConfig

        @Parcelize
        data class Details(val coinId: CoinId) : ChildConfig
    }
}