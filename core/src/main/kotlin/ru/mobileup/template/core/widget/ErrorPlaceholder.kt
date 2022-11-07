package ru.mobileup.template.core.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.R
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.coins_theme.CoinTheme

@Composable
fun ErrorPlaceholder(
    errorMessage: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.mipmap.ic_error_placeholder_round),
            contentDescription = "Error placeholder image",
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = stringResource(R.string.error_placeholder_text),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp, bottom = 32.dp),
            style = MaterialTheme.typography.body2
        )
        Button(
            onClick = onRetryClick,
            modifier = Modifier.widthIn(175.dp)
        ) {
            Text(
                text = stringResource(R.string.common_retry).uppercase(),
                color = CoinTheme.colors.surface,
                style = CoinTheme.typography.text.medium
            )
        }
    }
}

@Preview
@Composable
fun ErrorPlaceholderPreview() {
    AppTheme {
        ErrorPlaceholder(
            errorMessage = "Error message",
            onRetryClick = {}
        )
    }
}