package org.mifos.client.android.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.mifos.client.android.R
import org.mifos.client.android.data.api_services.search.SearchedEntity
import org.mifos.client.android.ui.components.LinkText
import org.mifos.client.android.ui.components.ProfileImagePlaceholder
import org.mifos.client.android.ui.components.RadioGroup
import org.mifos.client.android.ui.theme.spacing
import org.mifos.client.android.ui.theme.typographyExtra

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchString: String = "",
    setSearchString: (String) -> Unit = {},
    searchType: SearchType = SearchType.All,
    setSearchType: (SearchType) -> Unit = {},
    searchResults: List<SearchedEntity> = listOf(),
    onSearchEntityClick : (SearchedEntity) -> Unit = {},
    isLoading: Boolean,
    isExactMatchEnabled: Boolean,
    setIsExactMatchEnabled: (Boolean) -> Unit,
    effectFlow: Flow<SearchScreenEffects>,
    isErrorState: Boolean,
    onRetry: () -> Unit
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var isSearchTypeSelectionDialogShowing by remember { mutableStateOf(false) }
    val snackBarState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(Unit){
        effectFlow.onEach {
            if(it == SearchScreenEffects.ErrorLoadingList)
                snackBarState.showSnackbar(
                    message = context.getString(R.string.error_occured_message_default),
                    actionLabel = context.getString(R.string.ok)
                )
        }.collect()
    }

    if(isSearchTypeSelectionDialogShowing){
        AlertDialog(
            onDismissRequest = { isSearchTypeSelectionDialogShowing = false },
            text = {
                Column {
                    Row(
                        Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .toggleable(
                                value = isExactMatchEnabled,
                                onValueChange = { setIsExactMatchEnabled(!isExactMatchEnabled) },
                                role = Role.Checkbox
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isExactMatchEnabled,
                            onCheckedChange = null
                        )
                        Text(
                            text = stringResource(R.string.only_exact_matches),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    RadioGroup(
                        choices = SearchType.values().map { stringResource(it.title) },
                        selectedChoiceIndex = searchType.ordinal,
                        onChoiceSelection = { index ->
                            setSearchType(SearchType.values()[index])
                        }
                    )
                }
            },
            confirmButton = {
                Button(onClick = { isSearchTypeSelectionDialogShowing = false }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        )
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(id = R.string.search)) },
                actions = {
                    TextButton(onClick = { isSearchTypeSelectionDialogShowing = true }){
                        Text(text = stringResource(id = searchType.title))
                        Icon(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = {
            SnackbarHost(snackBarState)
        }
    ) { paddingValues ->
        val appBarContainerColor by animateColorAsState(
            targetValue = lerp(
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                FastOutLinearInEasing.transform(if (scrollBehavior.state.overlappedFraction > 0.01f) 1f else 0f)
            ),
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        )

        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxWidth()) {
            TextField(
                value = searchString,
                onValueChange = setSearchString,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    if(searchString.isNotBlank())
                        IconButton(
                            onClick = { setSearchString("") }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = stringResource(R.string.clear_search_query)
                            )
                        }
                },
                placeholder = { Text(stringResource(R.string.enter_search_query_here)) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = appBarContainerColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ){
                when{
                    searchString.isBlank() ->{
                        Text(
                            text = stringResource(R.string.search_query_is_empty),
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typographyExtra.label
                        )
                    }
                    isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                    isErrorState -> {
                        Column(modifier = Modifier.align(Alignment.Center),) {
                            Text(
                                text = stringResource(R.string.error_occured_message_default),
                                style = MaterialTheme.typographyExtra.label
                            )
                            LinkText(
                                text = stringResource(R.string.retry),
                                onClick = { onRetry() }
                            )
                        }
                    }
                    searchResults.isEmpty() -> {
                        Text(
                            text = stringResource(R.string.no_results_found),
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typographyExtra.label
                        )
                    }
                    else -> LazyColumn(Modifier.fillMaxWidth()){
                        items(searchResults){ item ->
                            SearchResultItem(
                                modifier = Modifier
                                    .padding(bottom = MaterialTheme.spacing.marginBetweenItemsSmall)
                                    .clickable { onSearchEntityClick(item) }
                                    .fillMaxWidth(),
                                searchResultEntity = item
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultItem(
    modifier: Modifier = Modifier,
    searchResultEntity: SearchedEntity
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImagePlaceholder(
            modifier = Modifier.padding(start = MaterialTheme.spacing.cardInnerPaddingMedium),
            character = searchResultEntity.entityName.run { if(this.isNotBlank()) first() else '.'  }
        )
        Text(
            text = searchResultEntity.run { "#$entityId - $entityName"  },
            style = MaterialTheme.typographyExtra.cardTitle,
            modifier = Modifier.padding(MaterialTheme.spacing.cardInnerPaddingMedium),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}