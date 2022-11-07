package ru.mobileup.template.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import ru.mobileup.template.core.theme.LightAppColors
import ru.mobileup.template.core.theme.coins_theme.AppTheme
import ru.mobileup.template.core.theme.toMaterialColors
import ru.mobileup.template.core.theme.toMaterialTypography

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) LightAppColors else LightAppColors
    val typography = AppTypography

    AppTheme(colors, typography) {
        MaterialTheme(
            colors = colors.toMaterialColors(),
            typography = typography.toMaterialTypography(),
            content = content
        )
    }
}