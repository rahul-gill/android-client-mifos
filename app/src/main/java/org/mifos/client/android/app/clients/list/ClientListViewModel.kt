package org.mifos.client.android.app.clients.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mifos.client.android.data.api_services.client.Client
import org.mifos.client.android.data.api_services.client.ClientService
import javax.inject.Inject


class ClientsPagingSource(
    private val clientService: ClientService
) : PagingSource<Int, Client>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Client> =  try {
        val nextPageNumber = params.key ?: 1
        val response = clientService.getAllClients(nextPageNumber, 10)
        LoadResult.Page(
            data = response.pageItems,
            prevKey = null, // Only paging forward.
            nextKey = if (response.pageItems.isEmpty()) null else nextPageNumber + 1
        )
    } catch (e: Exception){
        LoadResult.Error(e)
    }


    override fun getRefreshKey(state: PagingState<Int, Client>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}


@HiltViewModel
class ClientListViewModel @Inject constructor(
    private val clientService: ClientService
) : ViewModel() {
    val clientPagedData = Pager(PagingConfig(pageSize = 10)) {
        ClientsPagingSource(clientService)
    }.flow.cachedIn(viewModelScope)

}