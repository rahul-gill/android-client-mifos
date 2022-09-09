package org.mifos.client.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.mifos.client.android.ui.theme.size

enum class SingleChoiceFieldState {
    NotApplicableYet, Loading, OK
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SingleChoiceField(
    modifier: Modifier = Modifier,
    options: List<T>,
    selectOptionWithIndex: (index: Int) -> Unit,
    selectedOptionIndex: Int,
    optionText: T.() -> String,
    label: @Composable () -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    state: SingleChoiceFieldState = SingleChoiceFieldState.OK
){
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value =
                if(selectedOptionIndex != -1) options[selectedOptionIndex].optionText()
                else "",
            onValueChange = {},
            readOnly = true,
            label = label,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            trailingIcon = {
                if(state == SingleChoiceFieldState.Loading)
                    CircularProgressIndicator(Modifier.size(MaterialTheme.size.iconMedium))
                else
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier.fillMaxWidth(),
            enabled = state != SingleChoiceFieldState.NotApplicableYet
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectOptionWithIndex(index)
                        expanded = false
                    },
                    text = { Text(text = selectionOption.optionText()) }
                )
            }
        }
    }
}