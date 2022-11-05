package ru.mobileup.template.features.crypto.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.theme.coins_theme.CoinTheme
import ru.mobileup.template.core.widget.BottomShadow
import ru.mobileup.template.core.widget.TopShadow

@Composable
fun DetailsBarUi(
    coinName: String,
    onBackButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        TopShadow()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 60.dp)
        ) {
            IconButton(
                onClick = { onBackButtonPressed() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = CoinTheme.colors.arrowColor
                )
            }

            Text(
                text = coinName,
                style = CoinTheme.typography.topBar.semiBold,
                color = CoinTheme.colors.text.title,
                modifier = Modifier.padding(start = 36.dp)
            )
        }
        BottomShadow()
    }
}