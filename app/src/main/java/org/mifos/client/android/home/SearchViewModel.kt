package org.mifos.client.android.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import org.mifos.client.android.R
import org.mifos.client.android.auth.LoginScreenEffects
import org.mifos.client.android.data.api_services.search.SearchService
import org.mifos.client.android.data.api_services.search.SearchedEntity
import javax.inject.Inject


enum class SearchType{
    All, OnlyClients, OnlyGroups, OnlyLoanAccounts, OnlySavingsAccounts
}

val SearchType.title
    get() = when(this){
        SearchType.All -> R.string.all
        SearchType.OnlyClients -> R.string.clients
        SearchType.OnlyGroups -> R.string.groups
        SearchType.OnlyLoanAccounts -> R.string.loan_accounts
        SearchType.OnlySavingsAccounts -> R.string.savings_accounts
    }

val SearchType.apiResource
    get() = when(this){
        SearchType.All -> "clients,groups,loans,savingsaccounts"
        SearchType.OnlyClients -> "clients"
        SearchType.OnlyGroups -> "groups"
        SearchType.OnlyLoanAccounts -> "loans"
        SearchType.OnlySavingsAccounts -> "savingsaccounts"
    }

enum class SearchScreenEffects{
    ErrorLoadingList
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchService: SearchService
): ViewModel() {
    val searchString = mutableStateOf("")
    val searchType = mutableStateOf(SearchType.All)
    var searchJob = Job()
    val searchResults = mutableStateOf<List<SearchedEntity>>(listOf())
    val isExactMatchEnabled = mutableStateOf(false)
    val isLoadingResults = mutableStateOf(false)


    private val effects =  Channel<SearchScreenEffects>(Channel.UNLIMITED)
    val effectsFlow
        get() = effects.receiveAsFlow()

    fun setSearchString(newValue: String){
        searchString.value = newValue
        runSearch()
    }

    fun setSearchType(newValue: SearchType){
        searchType.value = newValue
        runSearch()
    }

    private fun runSearch(){
        searchJob.cancel()
        isLoadingResults.value = false
        searchJob = Job()
        (viewModelScope + searchJob).launch {
            isLoadingResults.value = true
            delay(300)
            searchService.searchResources(
                searchString.value,
                searchType.value.apiResource,
                isExactMatchEnabled.value
            ).onSuccess {
                println("onSuccess data:$data")
                isLoadingResults.value = false
                searchResults.value = this.data
            }.suspendOnFailure {
                println("suspendOnFailure error:${message()}")
                isLoadingResults.value = false
                effects.send(SearchScreenEffects.ErrorLoadingList)
            }
        }
    }
}