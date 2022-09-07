package org.mifos.client.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import org.mifos.client.android.ui.theme.size


@Preview(showBackground = true)
@Composable
fun ProfileImagePlaceholder(
    modifier: Modifier = Modifier,
    character: Char = 'A',
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    size: Dp = MaterialTheme.size.iconLarge,
    shape: CornerBasedShape = CircleShape
) {
    Box(
        modifier
            .clip(shape)
            .size(size)
            .background(backgroundColor)
    ) {
        Text(
            text = character.uppercaseChar().toString(),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = contentColorFor(backgroundColor),
                fontSize = with(LocalDensity.current) { size.toSp() / 1.3 }
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
