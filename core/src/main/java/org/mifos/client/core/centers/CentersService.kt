package org.mifos.client.core.centers

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.common.Page
import de.jensklingenberg.ktorfit.http.*

interface CentersService {

    @GET("/centers")
    fun getCenters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("paged") paged: Boolean = true
    ): ApiResponseFlow<Page<Center>>

    @GET("/centers/{centerId}/accounts")
    fun getCenterAccounts(@Path("centerId") centerId: Int): ApiResponseFlow<CenterAccountsResponse>


    @GET("/centers/{centerId}?associations=groupMembers,collectionMeetingCalendar")
    fun getCenterWithGroupMembersAndCollectionMeetingCalendar(@Path("centerId") centerId: Int): ApiResponseFlow<Center>


    @GET("/centers")
    fun getAllCentersInOffice(
        @Query("officeId") officeId: Int,
        @Query("limit") limit: Int = -1,
        @Query("orderBy") orderBy: String = "name",
        @Query("sortOrder") sortOrder: String = "ASC",
        @Query("staffId") staffId: Int? = null,
    ): ApiResponseFlow<List<Center>>


    @GET("/centers/{centerId}?associations=groupMembers")
    fun getAllGroupsForCenter(@Path("centerId") centerId: Int): ApiResponseFlow<Center>


    @POST("/centers/{centerId}?command=generateCollectionSheet")
    fun getCollectionSheet(
        @Path("centerId") centerId: Long,
        @Body payload: GetCollectionSheetPayload
    ): ApiResponseFlow<SaveCollectionSheetResponse>

}