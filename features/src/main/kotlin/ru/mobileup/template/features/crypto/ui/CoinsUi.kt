package ru.mobileup.template.features.crypto.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.utils.createFakeChildStack
import ru.mobileup.template.features.crypto.ui.coins_list.CoinsListUi
import ru.mobileup.template.features.crypto.ui.coins_list.FakeCoinsListComponent
import ru.mobileup.template.features.crypto.ui.details.CoinDetailsUi

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun CoinsUi(
    component: CoinsComponent,
    modifier: Modifier = Modifier
) {
    Children(
        stack = component.childStack,
        modifier = modifier
    ) { child ->
        when (val instance = child.instance) {
            is CoinsComponent.Child.List -> CoinsListUi(component = instance.component)
            is CoinsComponent.Child.Details -> CoinDetailsUi(component = instance.component)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CoinsUiPreview() {
    AppTheme {
        CoinsUi(FakeCoinsComponent())
    }
}

class FakeCoinsComponent : CoinsComponent {
    override val childStack = createFakeChildStack(
        CoinsComponent.Child.List(FakeCoinsListComponent())
    )
}
