package org.mifos.client.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.mifos.client.android.home.SearchType
import org.mifos.client.android.home.title
import org.mifos.client.android.ui.theme.spacing

@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    choices: List<String>,
    selectedChoiceIndex: Int,
    onChoiceSelection: (index: Int) -> Unit
){
    Column(Modifier.selectableGroup().then(modifier)) {
        choices.forEachIndexed { index, choiceItem ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (index == selectedChoiceIndex),
                        onClick = {
                            onChoiceSelection(index)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = MaterialTheme.spacing.cardInnerPaddingMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (index == selectedChoiceIndex),
                    onClick = null
                )
                Text(
                    text = choiceItem,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.marginBetweenItemsMedium)
                )
            }
        }
    }
}