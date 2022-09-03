package org.mifos.client.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mifos.client.android.ui.theme.Alpha
import org.mifos.client.android.ui.theme.size
import org.mifos.client.android.ui.theme.spacing


@Composable
fun PinCodeField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    charColor: Color = MaterialTheme.colorScheme.onSurface,
    charBackground: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = Alpha.lowest),
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    containerSize: Dp = MaterialTheme.size.iconLarge,
    characterCount: Int = 6,
    enabled: Boolean = true,
    password: Boolean = false,
    passwordChar: String = "*",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
) {
    CompositionLocalProvider(
        LocalTextInputService provides null
    ) {
        BasicTextField(
            modifier = modifier,
            value = text,
            onValueChange = {
                if (it.length <= characterCount) {
                    onTextChange.invoke(it)
                }
            },
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            decorationBox = {
                Row(horizontalArrangement = Arrangement.SpaceAround) {
                    repeat(characterCount) { index ->
                        Spacer(modifier = Modifier.width(2.dp))
                        CharView(
                            index = index,
                            text = text,
                            charColor = charColor,
                            textStyle = textStyle,
                            containerSize = containerSize,
                            charBackground = charBackground,
                            password = password,
                            passwordChar = passwordChar,
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                }
            },
        )
    }
}


@Composable
private fun CharView(
    index: Int,
    text: String,
    charColor: Color,
    textStyle: TextStyle,
    containerSize: Dp,
    charBackground: Color = Color.Transparent,
    password: Boolean = false,
    passwordChar: String = "*"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .size(containerSize)
            .background(charBackground)
    ) {
        Text(
            text = when {
                index >= text.length -> ""
                password -> passwordChar
                else -> text[index].toString()
            },
            color = charColor,
            style = textStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.cardInnerPaddingSmall)
        )
    }
}