package org.mifos.client.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Size(
    val thinLine: Dp = 1.dp,
    val iconSmall: Dp = 15.dp,
    val iconMedium: Dp = 24.dp,
    val iconLarge: Dp = 39.dp,
    val imageSmall: Dp = 64.dp,
    val imageMedium: Dp = 120.dp,
    val imageLarge: Dp = 170.dp,
    val cardSmall: Dp = 90.dp,
    val cardMedium: Dp = 180.dp,
    val cardLarge: Dp = 300.dp
)

internal val LocalSize = staticCompositionLocalOf { Size() }


val MaterialTheme.size: Size
    @Composable
    @ReadOnlyComposable
    get() = LocalSize.current