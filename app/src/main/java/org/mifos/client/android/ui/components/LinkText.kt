package org.mifos.client.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import org.mifos.client.android.ui.theme.Alpha


@Composable
fun LinkText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    onClick: () -> Unit = { }
){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val textDecoration by remember {
        derivedStateOf{
            when{
                !isPressed && !isHovered -> TextDecoration.Underline
                else -> TextDecoration.None
            }
        }
    }
    val textModifier = modifier.clickable(interactionSource, null, onClick = onClick)

    Text(text, modifier = textModifier,
        color = when{
            isPressed -> MaterialTheme.colorScheme.primary.copy(alpha = Alpha.low)
            isHovered -> MaterialTheme.colorScheme.primary.copy(alpha = Alpha.low)
            else -> MaterialTheme.colorScheme.primary
        },
        fontSize, fontStyle, fontWeight, fontFamily, letterSpacing,
        textDecoration = textDecoration,
        textAlign, lineHeight, overflow, softWrap, maxLines, onTextLayout, style)
}
