package org.mifos.client.android.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CorporateFare
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import dev.olshevski.navigation.reimagined.*
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import org.mifos.client.android.R
import org.mifos.client.android.home.*


enum class HomeNavigationScreen {
    SearchScreen, ClientListScreen, GroupListScreen, CenterListScreen
}

val HomeNavigationScreen.tabTitleId
    get() = when (this) {
        HomeNavigationScreen.SearchScreen -> R.string.search
        HomeNavigationScreen.ClientListScreen -> R.string.clients
        HomeNavigationScreen.GroupListScreen -> R.string.groups
        HomeNavigationScreen.CenterListScreen -> R.string.centers
    }

val HomeNavigationScreen.tabIcon
    get() = when (this) {
        HomeNavigationScreen.SearchScreen -> Icons.Outlined.Search
        HomeNavigationScreen.ClientListScreen -> Icons.Outlined.Person
        HomeNavigationScreen.GroupListScreen -> Icons.Outlined.Group
        HomeNavigationScreen.CenterListScreen -> Icons.Outlined.CorporateFare
    }

@Composable
fun HomeNavigationNavHost() {
    val navController = rememberNavController(startDestination = HomeNavigationScreen.SearchScreen)
    BottomNavigationBackHandler(navController)


    Column {
        Box(Modifier.weight(1f)) {
            NavHost(
                controller = navController
            ) { screen ->
                when(screen){
                    HomeNavigationScreen.SearchScreen -> {
                        val viewModel = hiltViewModel<SearchViewModel>()

                        SearchScreen(
                            searchString = viewModel.searchString.value,
                            setSearchString = { viewModel.setSearchString(it) },
                            searchType = viewModel.searchType.value,
                            setSearchType = { viewModel.setSearchType(it) },
                            isLoading = viewModel.isLoadingResults.value,
                            isExactMatchEnabled = viewModel.isExactMatchEnabled.value,
                            setIsExactMatchEnabled = { viewModel.isExactMatchEnabled.value = it },
                            searchResults = viewModel.searchResults.value,
                            effectFlow = viewModel.effectsFlow,
                            isErrorState = viewModel.isErrorState.value,
                            onRetry = { viewModel.runSearch() }
                        )
                    }
                    HomeNavigationScreen.CenterListScreen -> {
                        val viewModel: CenterListViewModel = hiltViewModel()
                        CenterListScreen(
                            centerList = viewModel.centerPagedData.collectAsLazyPagingItems(),
                            onCreateNewCenter = { /*TODO*/ },
                            onCenterClick = { /*TODO*/ }
                        )
                    }
                    HomeNavigationScreen.ClientListScreen -> {
                        val viewModel = hiltViewModel<ClientListViewModel>()
                        ClientListScreen(
                            clientList = viewModel.clientPagedData.collectAsLazyPagingItems(),
                            onCreateNewClient = { /*TODO*/ },
                            onClientClick = { /*TODO*/ }
                        )
                    }
                    HomeNavigationScreen.GroupListScreen -> {
                        val viewModel : GroupListViewModel = hiltViewModel()
                        GroupListScreen(
                            groupList = viewModel.groupPagedData.collectAsLazyPagingItems(),
                            onCreateNewGroup = { /*TODO*/ },
                            onGroupClick = { /*TODO*/ }
                        )
                    }
                }
            }
        }

        val lastDestination = navController.backstack.entries.last().destination
        NavigationBar {
            HomeNavigationScreen.values().forEach { destination ->
                val tabTitle = stringResource(destination.tabTitleId)
                NavigationBarItem(
                    label = { Text(tabTitle) },
                    icon = {
                        Icon(
                            imageVector = destination.tabIcon,
                            contentDescription = tabTitle
                        )
                    },
                    selected = destination == lastDestination,
                    onClick = {
                        // keep only one instance of a destination in the backstack
                        if (!navController.moveToTop { it == destination }) {
                            // if there is no existing instance, add it
                            navController.navigate(destination)
                        }
                    }
                )
            }
        }
    }
}


@Composable
private fun BottomNavigationBackHandler(
    navController: NavController<HomeNavigationScreen>
) {
    BackHandler(enabled = navController.backstack.entries.size > 1) {
        val lastEntry = navController.backstack.entries.last()
        if (lastEntry.destination == HomeNavigationScreen.values()[0]) {
            // The start destination should always be the last to pop. We move it to the start
            // to preserve its saved state and view models.
            navController.moveLastEntryToStart()
        } else {
            navController.pop()
        }
    }
}

private fun NavController<HomeNavigationScreen>.moveLastEntryToStart() {
    setNewBackstack(
        entries = backstack.entries.toMutableList().also {
            val entry = it.removeLast()
            it.add(0, entry)
        },
        action = NavAction.Pop
    )
}