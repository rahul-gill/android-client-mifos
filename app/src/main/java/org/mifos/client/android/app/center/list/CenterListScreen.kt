package org.mifos.client.android.app.center.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.mifos.client.android.R
import org.mifos.client.android.data.api_services.centers.Center
import org.mifos.client.android.ui.components.LinkText
import org.mifos.client.android.ui.theme.colorSchemeExtra
import org.mifos.client.android.ui.theme.size
import org.mifos.client.android.ui.theme.spacing
import org.mifos.client.android.ui.theme.typographyExtra

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterListScreen(
    centerList: LazyPagingItems<Center>,
    onCreateNewCenter: () -> Unit,
    onCenterClick: (Center) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(id = R.string.centers)) },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onCreateNewCenter() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.create_center))
            }
        },
    ) { paddingValues ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = centerList.loadState.refresh == LoadState.Loading),
            onRefresh = { centerList.refresh() },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (centerList.loadState.refresh is LoadState.Error) {
                Column(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.screenHorizontalPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = (centerList.loadState.refresh as LoadState.Error).error.message.toString())
                    LinkText(
                        text = stringResource(id = R.string.retry),
                        onClick = { centerList.retry() }
                    )
                }
            }

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                itemsIndexed(centerList) { index, center ->
                    if (center != null) CenterListItem(
                        modifier = Modifier
                            .clickable { onCenterClick(center) }
                            .fillMaxWidth(),
                        center = center
                    )
                    if(index != centerList.itemCount)
                        Divider()
                }
                when (centerList.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    LoadState.Loading -> {
                        item { CircularProgressIndicator() }
                    }
                    is LoadState.Error -> {
                        item {
                            Text(
                                text = (centerList.loadState.append as LoadState.Error).error.message.toString(),
                                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.screenHorizontalPadding)
                            )
                            LinkText(
                                text = stringResource(id = R.string.retry),
                                onClick = { centerList.retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CenterListItem(
    modifier: Modifier = Modifier,
    center: Center
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.cardInnerPaddingMedium)
                .size(MaterialTheme.size.iconSmall)
                .clip(CircleShape)
                .background(if (center.active) MaterialTheme.colorSchemeExtra.success else MaterialTheme.colorScheme.error)
        )
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.cardInnerPaddingMedium)
        ) {
            Row(Modifier.padding(bottom = MaterialTheme.spacing.cardInnerPaddingSmall)) {
                Text(
                    text = center.name,
                    style = MaterialTheme.typographyExtra.cardTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = center.id.toString(),
                    style = MaterialTheme.typographyExtra.cardSubtitle,
                    maxLines = 1
                )
            }
            Text(
                text = stringResource(R.string.account_number, center.accountNo),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typographyExtra.label,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.marginBetweenItemsSmall)
            )
            Text(
                text = stringResource(R.string.office_name_with_id, center.officeName , center.officeId),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typographyExtra.label,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.marginBetweenItemsSmallest)
            )
            Text(
                text =
                    if(center.staffName != null)
                        stringResource(R.string.staff_name_with_id, center.staffName, center.staffId.toString())
                    else
                        stringResource(id = R.string.no_staff),
                maxLines = 1,
                style = MaterialTheme.typographyExtra.label,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}
