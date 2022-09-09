package org.mifos.client.android.app.clients.list

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
import org.mifos.client.android.data.api_services.client.Client
import org.mifos.client.android.ui.components.LinkText
import org.mifos.client.android.ui.components.ProfileImagePlaceholder
import org.mifos.client.android.ui.theme.spacing
import org.mifos.client.android.ui.theme.typographyExtra

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientListScreen(
    clientList: LazyPagingItems<Client>,
    onCreateNewClient: () -> Unit,
    onClientClick: (Client) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(id = R.string.clients)) },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onCreateNewClient() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.create_client))
            }
        },
    ) { paddingValues ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = clientList.loadState.refresh == LoadState.Loading),
            onRefresh = { clientList.refresh() },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (clientList.loadState.refresh is LoadState.Error) {
                Column(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.screenHorizontalPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = (clientList.loadState.refresh as LoadState.Error).error.message.toString())
                    LinkText(
                        text = stringResource(id = R.string.retry),
                        onClick = { clientList.retry() }
                    )
                }
            }

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                itemsIndexed(clientList) { index, client ->
                    if (client != null) ClientListItem(
                        modifier = Modifier
                            .clickable { onClientClick(client) }
                            .fillMaxWidth(),
                        client = client
                    )
                    if(index != clientList.itemCount)
                        Divider()
                }
                when (clientList.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    LoadState.Loading -> {
                        item { CircularProgressIndicator() }
                    }
                    is LoadState.Error -> {
                        item {
                            Text(
                                text = (clientList.loadState.append as LoadState.Error).error.message.toString(),
                                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.screenHorizontalPadding)
                            )
                            LinkText(
                                text = stringResource(id = R.string.retry),
                                onClick = { clientList.retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClientListItem(
    modifier: Modifier = Modifier,
    client: Client
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImagePlaceholder(
            modifier = Modifier.padding(start = MaterialTheme.spacing.cardInnerPaddingMedium),
            character = client.firstname.run { if(this.isNotBlank()) first() else '.'  }
        )
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.cardInnerPaddingMedium)
        ) {
            Text(
                text = client.firstname + " " + client.middlename.run { if(this == null) "" else "$this " } + client.lastname,
                style = MaterialTheme.typographyExtra.cardTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.cardInnerPaddingSmall)
            )
            Text(
                text = client.accountNo,
                style = MaterialTheme.typographyExtra.cardSubtitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}
