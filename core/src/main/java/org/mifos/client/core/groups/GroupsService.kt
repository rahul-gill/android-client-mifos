package org.mifos.client.core.groups

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.core.common.Page
import retrofit2.http.*


interface GroupService {
    
    @GET("groups")
    suspend fun getGroups(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("paged") paged: Boolean = true,
    ): ApiResponse<Page<Group>>

    @GET("groups/{groupId}?associations=all")
    suspend fun getGroupWithAssociations(@Path("groupId") groupId: Int): ApiResponse<Group>

    @GET("groups")
    suspend fun getAllGroupsInOffice(
        @Query("officeId") officeId: Int,
        @Query("limit") limit: Int = -1,
        @Query("orderBy") orderBy: String = "name",
        @Query("sortOrder") sortOrder: String = "ASC",
        @Query("staffId") staffId: Int? = null,
    ): ApiResponse<List<Group>>

    @POST("groups")
    suspend fun createGroup(@Body groupPayload: CreateGroupPayload): ApiResponse<CreateGroupResponse>

    @GET("groups/{groupId}")
    suspend fun getGroup(@Path("groupId") groupId: Int): ApiResponse<Group>

    @GET("groups/{groupId}/accounts")
    suspend fun getGroupAccounts(@Path("groupId") groupId: Int): ApiResponse<GroupAccountsResponse>

    @POST("groups/{groupId}?command=activate")
    suspend fun activateGroup(
        @Path("groupId") groupId: Int,
        @Body activatePayload: ActivateGroupPayload
    ): ApiResponse<CreateGroupResponse>
}
