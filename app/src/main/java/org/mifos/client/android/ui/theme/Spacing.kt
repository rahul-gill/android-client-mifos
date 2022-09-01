package org.mifos.client.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val screenHorizontalPadding: Dp = 8.dp,
    val screenVerticalPadding: Dp = 8.dp,
    val marginBetweenItemsSmallest: Dp = 4.dp,
    val marginBetweenItemsSmall: Dp = 8.dp,
    val marginBetweenItemsMedium: Dp = 16.dp,
    val marginBetweenItemsLarge: Dp = 20.dp,
    val marginBetweenSections: Dp = 48.dp,
    val fabPadding: Dp = 16.dp,
    val listBottomPadding: Dp = 100.dp,
    val cardInnerPaddingSmall: Dp = 8.dp,
    val cardInnerPaddingMedium: Dp = 14.dp,
    val cardInnerPaddingLarge: Dp = 20.dp,
    val betweenListItems: Dp = 16.dp
)

internal val LocalSpacing = staticCompositionLocalOf { Spacing() }


val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current