package org.mifos.client.android.app.group.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mifos.client.android.data.api_services.groups.Group
import org.mifos.client.android.data.api_services.groups.GroupService
import javax.inject.Inject


class GroupsPagingSource(
    private val groupService: GroupService
) : PagingSource<Int, Group>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Group> =  try {
        val nextPageNumber = params.key ?: 1
        val response = groupService.getGroups(nextPageNumber, 10)

        println("response.pageItems: $response")
        LoadResult.Page(
            data = response.pageItems,
            prevKey = null, // Only paging forward.
            nextKey = if (response.pageItems.isEmpty()) null else nextPageNumber + 1
        )
    } catch (e: Exception){
        LoadResult.Error(e)
    }


    override fun getRefreshKey(state: PagingState<Int, Group>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}


@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val groupService: GroupService
) : ViewModel() {
    val groupPagedData = Pager(PagingConfig(pageSize = 10)) {
        GroupsPagingSource(groupService)
    }.flow.cachedIn(viewModelScope)

}