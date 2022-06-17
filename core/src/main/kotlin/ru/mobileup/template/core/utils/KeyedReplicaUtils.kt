package ru.mobileup.template.core.utils

import androidx.compose.runtime.State
import com.arkivanov.essenty.lifecycle.Lifecycle
import me.aartikov.replica.decompose.observe
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.Loadable
import ru.mobileup.template.core.error_handling.ErrorHandler

/**
 * Observes [KeyedReplica] and uses [ErrorHandler] to handle errors.
 */
fun <T : Any, K : Any> KeyedReplica<K, T>.observe(
    lifecycle: Lifecycle,
    errorHandler: ErrorHandler,
    key: () -> K?,
    keepPreviousData: Boolean = false
): State<Loadable<T>> {
    return observe(
        lifecycle,
        onError = { error, state ->
            errorHandler.handleError(
                exception = error.exception,
                showError = state.data != null // show error only if fullscreen error is not shown
            )
        },
        key,
        keepPreviousData
    )
}