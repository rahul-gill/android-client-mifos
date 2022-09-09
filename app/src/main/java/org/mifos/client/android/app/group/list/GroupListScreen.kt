package org.mifos.client.android.app.group.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.mifos.client.android.R
import org.mifos.client.android.data.api_services.groups.Group
import org.mifos.client.android.ui.components.LinkText
import org.mifos.client.android.ui.components.ProfileImagePlaceholder
import org.mifos.client.android.ui.theme.spacing
import org.mifos.client.android.ui.theme.typographyExtra


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupListScreen(
    groupList: LazyPagingItems<Group>,
    onCreateNewGroup: () -> Unit,
    onGroupClick: (Group) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(id = R.string.groups)) },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onCreateNewGroup() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.create_group))
            }
        },
    ) { paddingValues ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = groupList.loadState.refresh == LoadState.Loading),
            onRefresh = { groupList.refresh() },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (groupList.loadState.refresh is LoadState.Error) {
                Column(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.screenHorizontalPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = (groupList.loadState.refresh as LoadState.Error).error.message.toString())
                    LinkText(
                        text = stringResource(id = R.string.retry),
                        onClick = { groupList.retry() }
                    )
                }
            }

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                itemsIndexed(groupList) { index, group ->
                    if (group != null) GroupListItem(
                        modifier = Modifier
                            .clickable { onGroupClick(group) }
                            .fillMaxWidth(),
                        group = group
                    )
                    if(index != groupList.itemCount)
                        Divider()
                }
                when (groupList.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    LoadState.Loading -> {
                        item { CircularProgressIndicator() }
                    }
                    is LoadState.Error -> {
                        item {
                            Text(
                                text = (groupList.loadState.append as LoadState.Error).error.message.toString(),
                                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.screenHorizontalPadding)
                            )
                            LinkText(
                                text = stringResource(id = R.string.retry),
                                onClick = { groupList.retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GroupListItem(
    modifier: Modifier = Modifier,
    group: Group
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImagePlaceholder(
            modifier = Modifier.padding(start = MaterialTheme.spacing.cardInnerPaddingMedium),
            character = group.name.run { if(this.isNotBlank()) first() else '.'  }
        )
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.cardInnerPaddingMedium)
        ) {
            Text(
                text = group.name,
                style = MaterialTheme.typographyExtra.cardTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.cardInnerPaddingSmall)
            )
            Text(
                text = group.id.toString(),
                style = MaterialTheme.typographyExtra.cardSubtitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}
