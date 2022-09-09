package org.mifos.client.android.ui.navigation

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.compose.collectAsLazyPagingItems
import dev.olshevski.navigation.reimagined.*
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import kotlinx.parcelize.Parcelize
import org.mifos.client.android.app.clients.create.CreateClientScreen
import org.mifos.client.android.app.clients.create.CreateClientViewModel
import org.mifos.client.android.app.clients.list.ClientListScreen
import org.mifos.client.android.app.clients.list.ClientListViewModel

sealed class ClientNavigationScreen {
    @Parcelize
    object ClientListScreen : ClientNavigationScreen(), Parcelable

    @Parcelize
    class ClientDetailsScreen(val clientId: Int) : ClientNavigationScreen(), Parcelable

    @Parcelize
    object CreateClientScreen : ClientNavigationScreen(), Parcelable

}

@Composable
fun ClientNavigationNavHost(
    onNavigateToFromBottomNav: (shouldShowBottomNav: Boolean) -> Unit
) {
    val navController = rememberNavController<ClientNavigationScreen>(
        startDestination = ClientNavigationScreen.ClientListScreen
    )
    LaunchedEffect(navController.backstack){
        onNavigateToFromBottomNav(navController.backstack.entries.last().destination is ClientNavigationScreen.ClientListScreen)
    }
    NavBackHandler(navController)

    NavHost(navController) { screen ->
        when (screen) {
            ClientNavigationScreen.ClientListScreen -> {
                val viewModel = hiltViewModel<ClientListViewModel>()
                ClientListScreen(
                    clientList = viewModel.clientPagedData.collectAsLazyPagingItems(),
                    onCreateNewClient = {
                        onNavigateToFromBottomNav(false)
                        navController.navigate(ClientNavigationScreen.CreateClientScreen)
                    },
                    onClientClick = { client ->
                        onNavigateToFromBottomNav(false)
                        navController.navigate(ClientNavigationScreen.ClientDetailsScreen(client.id))
                    }
                )
            }
            ClientNavigationScreen.CreateClientScreen -> {
                val viewModel: CreateClientViewModel = hiltViewModel()
                CreateClientScreen(
                    userImageUri = viewModel.userImageUri.value,
                    setUserImageUrl = { viewModel.userImageUri.value = it },
                    firstName = viewModel.firstName.value,
                    setFirstName = { viewModel.firstName.value = it },
                    lastName = viewModel.lastName.value,
                    setLastName = { viewModel.lastName.value = it },
                    middleName = viewModel.middleName.value,
                    setMiddleName = { viewModel.middleName.value = it },
                    externalId = viewModel.externalId.value,
                    setExternalId = { viewModel.externalId.value = it },
                    mobilePhoneCountry = viewModel.mobileCountry.value,
                    setMobilePhoneCountry = { viewModel.mobileCountry.value = it },
                    mobileNumber = viewModel.mobileNumber.value,
                    setMobileNumber = { viewModel.mobileNumber.value = it },
                    dateOfBirth = viewModel.dateOfBirth.value,
                    setDateOfBirth = { viewModel.dateOfBirth.value = it },
                    goBack = { navController.pop() },
                    loadingState = viewModel.clientTemplateLoadingState.value,
                    errorState = viewModel.clientTemplateErrorState.value,
                    onRetry = { viewModel.loadClientTemplate() },
                    genderOptions = viewModel.genderOptions.value,
                    onGenderOptionSelection = { viewModel.selectedGender.value = it },
                    clientTypeOptions = viewModel.clientTypeOptions.value,
                    onClientTypeOptionSelection = { viewModel.selectedClientType.value = it },
                    clientClassificationOptions = viewModel.clientClassificationOptions.value,
                    onClientClassificationSelection = {
                        viewModel.selectedClientClassification.value = it
                    },
                    staffOptions = viewModel.staffOptions.value,
                    onStaffOptionSelection = { viewModel.selectedStaff.value = it },
                    officeOptions = viewModel.officeOptions.value,
                    onOfficeOptionSelection = {
                        viewModel.selectedOffice.value = it
                        viewModel.loadStaffsForOffice(it)
                    },
                    isClientActive = viewModel.isClientActive.value,
                    setIsClientActive = { viewModel.isClientActive.value = it },
                    activationDate = viewModel.activationDate.value,
                    setActivationDate = { viewModel.activationDate.value = it },
                    selectedGenderIndex = viewModel.selectedGender.value,
                    selectedClientClassificationIndex = viewModel.selectedClientClassification.value,
                    selectedClientTypeIndex = viewModel.selectedClientType.value,
                    selectedOfficeIndex = viewModel.selectedOffice.value,
                    selectedStaffIndex = viewModel.selectedStaff.value,
                    onSubmit = { viewModel.onSubmit() },
                    effectsFlow = viewModel.effectsFlow,
                    staffOptionsState = viewModel.staffOptionsState.value
                )
            }
            is ClientNavigationScreen.ClientDetailsScreen -> {

            }
        }
    }
}