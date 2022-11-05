package ru.mobileup.template.core.widget

import android.widget.ImageView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import ru.mobileup.template.core.R

@Composable
fun TopShadow(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                setImageDrawable(ContextCompat.getDrawable(context, R.drawable.toolbar_top_shadow))
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 2.dp)
    )
}

@Composable
fun BottomShadow(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                setImageDrawable(ContextCompat.getDrawable(context, R.drawable.toolbar_bottom_shadow))
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 4.dp)
    )
}