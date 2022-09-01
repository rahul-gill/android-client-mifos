package org.mifos.client.core.groups

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.common.Page
import de.jensklingenberg.ktorfit.http.*


interface GroupService {
    
    @GET("/groups")
    fun getGroups(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("paged") paged: Boolean = true,
    ): ApiResponseFlow<Page<Group>>

    @GET("/groups/{groupId}?associations=all")
    fun getGroupWithAssociations(@Path("groupId") groupId: Int): ApiResponseFlow<Group>

    @GET("/groups")
    fun getAllGroupsInOffice(
        @Query("officeId") officeId: Int,
        @Query("limit") limit: Int = -1,
        @Query("orderBy") orderBy: String = "name",
        @Query("sortOrder") sortOrder: String = "ASC",
        @Query("staffId") staffId: Int? = null,
    ): ApiResponseFlow<List<Group>>

    @POST("/groups")
    fun createGroup(@Body groupPayload: CreateGroupPayload): ApiResponseFlow<CreateGroupResponse>

    @GET("/groups/{groupId}")
    fun getGroup(@Path("groupId") groupId: Int): ApiResponseFlow<Group>

    @GET("/groups/{groupId}/accounts")
    fun getGroupAccounts(@Path("groupId") groupId: Int): ApiResponseFlow<GroupAccountsResponse>

    @POST("/groups/{groupId}?command=activate")
    fun activateGroup(
        @Path("groupId") groupId: Int,
        @Body activatePayload: ActivateGroupPayload
    ): ApiResponseFlow<CreateGroupResponse>
}
