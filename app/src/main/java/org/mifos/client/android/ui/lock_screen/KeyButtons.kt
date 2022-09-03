package org.mifos.client.android.ui.lock_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.mifos.client.android.ui.theme.spacing

@Composable
fun KeyButton(
    symbol: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    textStyle: TextStyle = MaterialTheme.typography.headlineLarge,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(color)
            .clickable {
                onClick()
            }
            .size(75.dp)
            .then(modifier),
    ) {
        Text(text = symbol, style = textStyle.copy(color = contentColorFor(color)))
    }
}

@Composable
fun KeyButton(
    symbolIcon: ImageVector,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(color)
            .clickable {
                onClick()
            }
            .size(75.dp)
            .then(modifier),
    ) {
        Icon(
            imageVector = symbolIcon,
            contentDescription = null,
            tint = contentColorFor(color)
        )
    }
}

@Composable
fun KeyButtons(
    modifier: Modifier = Modifier,
    enterKey: (String) -> Unit,
    clearKey: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.screenHorizontalPadding)
            .then(modifier)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.marginBetweenItemsSmall),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.marginBetweenItemsSmall)
            ) {
                KeyButton(
                    symbol = "1"
                ) {
                    enterKey("1")
                }
                KeyButton(
                    symbol = "2"
                ) {
                    enterKey("2")
                }
                KeyButton(
                    symbol = "3"
                ) {
                    enterKey("3")
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.marginBetweenItemsSmall)
            ) {
                KeyButton(
                    symbol = "4"
                ) {
                    enterKey("4")
                }
                KeyButton(
                    symbol = "5"
                ) {
                    enterKey("5")
                }
                KeyButton(
                    symbol = "6"
                ) {
                    enterKey("6")
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.marginBetweenItemsSmall)
            ) {
                KeyButton(
                    symbol = "7"
                ) {
                    enterKey("7")
                }
                KeyButton(
                    symbol = "8"
                ) {
                    enterKey("8")
                }
                KeyButton(
                    symbol = "9"
                ) {
                    enterKey("9")
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.marginBetweenItemsSmall)
            ) {
                Spacer(modifier = Modifier.size(75.dp))
                KeyButton(
                    symbol = "0"
                ) {
                    enterKey("0")
                }
                KeyButton(
                    symbolIcon = Icons.Outlined.Backspace,
                    color = MaterialTheme.colorScheme.errorContainer,
                ) {
                    clearKey()
                }
            }
        }
    }
}
