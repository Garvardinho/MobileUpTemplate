package ru.mobileup.template.features.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import ru.mobileup.template.core.message.ui.MessageComponent
import ru.mobileup.template.features.crypto.ui.CoinsComponent

/**
 * A root of a Decompose component tree.
 *
 * Note: Try to minimize child count in RootComponent. It should operate by flows of screens rather than separate screens.
 */
interface RootComponent {

    val childStack: ChildStack<*, Child>

    val messageComponent: MessageComponent

    sealed interface Child {
        class Coins(val component: CoinsComponent) : Child
    }
}