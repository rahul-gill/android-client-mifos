package org.mifos.client.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

object TypographyExtra {

    val heading: TextStyle
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography.headlineSmall
    val subHeading: TextStyle
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography.titleMedium
    val mainFocusText
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
    val bodyLarger
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    val body: TextStyle
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography.bodyMedium
    val label: TextStyle
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography.labelMedium.copy(
            MaterialTheme.colorScheme.onSurface.copy(
                alpha = Alpha.low
            )
        )
    val cardTitle: TextStyle
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography.bodyLarge
    val cardSubtitle: TextStyle
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography.bodyMedium.copy(
            MaterialTheme.colorScheme.onSurface.copy(
                alpha = Alpha.low
            )
        )
}

val MaterialTheme.typographyExtra: TypographyExtra
    @Composable
    @ReadOnlyComposable
    get() = TypographyExtra

