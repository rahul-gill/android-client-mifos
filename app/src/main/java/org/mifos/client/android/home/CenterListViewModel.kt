package org.mifos.client.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mifos.client.android.data.api_services.centers.Center
import org.mifos.client.android.data.api_services.centers.CentersService
import org.mifos.client.android.data.api_services.client.Client
import org.mifos.client.android.data.api_services.client.ClientService
import javax.inject.Inject


class CentersPagingSource(
    private val centerService: CentersService
) : PagingSource<Int, Center>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Center> =  try {
        val nextPageNumber = params.key ?: 1
        val response = centerService.getCenters(nextPageNumber, 10)

        LoadResult.Page(
            data = response.pageItems,
            prevKey = null, // Only paging forward.
            nextKey = if (response.pageItems.isEmpty()) null else nextPageNumber + 1
        )
    } catch (e: Exception){
        LoadResult.Error(e)
    }


    override fun getRefreshKey(state: PagingState<Int, Center>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}


@HiltViewModel
class CenterListViewModel @Inject constructor(
    private val centerService: CentersService
) : ViewModel() {
    val centerPagedData = Pager(PagingConfig(pageSize = 10)) {
        CentersPagingSource(centerService)
    }.flow.cachedIn(viewModelScope)

}