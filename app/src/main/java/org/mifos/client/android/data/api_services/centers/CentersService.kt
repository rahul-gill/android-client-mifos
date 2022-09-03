package org.mifos.client.android.data.api_services.centers

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.android.data.api_services.common.Page
import retrofit2.http.*

interface CentersService {

    @GET("centers")
    suspend fun getCenters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("paged") paged: Boolean = true
    ): ApiResponse<Page<Center>>

    @GET("centers/{centerId}/accounts")
    suspend fun getCenterAccounts(@Path("centerId") centerId: Int): ApiResponse<CenterAccountsResponse>


    @GET("centers/{centerId}?associations=groupMembers,collectionMeetingCalendar")
    suspend fun getCenterWithGroupMembersAndCollectionMeetingCalendar(@Path("centerId") centerId: Int): ApiResponse<Center>


    @GET("centers")
    suspend fun getAllCentersInOffice(
        @Query("officeId") officeId: Int,
        @Query("limit") limit: Int = -1,
        @Query("orderBy") orderBy: String = "name",
        @Query("sortOrder") sortOrder: String = "ASC",
        @Query("staffId") staffId: Int? = null,
    ): ApiResponse<List<Center>>


    @GET("centers/{centerId}?associations=groupMembers")
    suspend fun getAllGroupsForCenter(@Path("centerId") centerId: Int): ApiResponse<Center>


    @POST("centers/{centerId}?command=generateCollectionSheet")
    suspend fun getCollectionSheet(
        @Path("centerId") centerId: Long,
        @Body payload: GetCollectionSheetPayload
    ): ApiResponse<SaveCollectionSheetResponse>

}