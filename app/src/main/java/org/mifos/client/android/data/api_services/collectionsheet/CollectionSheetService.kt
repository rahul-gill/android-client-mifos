package org.mifos.client.android.data.api_services.collectionsheet

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.android.data.ApiDefaults
import org.mifos.client.android.data.api_services.common.GenericResponse
import retrofit2.http.*

interface CollectionSheetService {
    @POST("collectionsheet?command=generateCollectionSheet")
    suspend fun getIndividualCollectionSheet(
        @Body payload: GenerateCollectionSheetPayload
    ): ApiResponse<IndividualCollectionSheet>

    @POST("collectionsheet?command=saveCollectionSheet")
    suspend fun saveIndividualCollectionSheet(
        @Body payload: SaveCollectionSheetPayload
    ): ApiResponse<GenericResponse>

    @GET("centers")
    suspend fun fetchCenterDetails(
        @Query("dateFormat") format: String = ApiDefaults.API_DATE_FORMAT,
        @Query("locale") locale: String = ApiDefaults.API_LOCALE,
        @Query("meetingDate") meetingDate: String,
        @Query("officeId") officeId: Int,
        @Query("staffId") staffId: Int
    ): ApiResponse<List<CenterDetails>>

    @POST("centers/{centerId}?command=generateCollectionSheet")
    suspend fun fetchProductiveSheet(
        @Path("centerId") centerId: Int,
        @Body payload: CollectionSheetRequestPayload
    ): ApiResponse<CollectionSheetResponse>

    @POST("centers/{centerId}?command=saveCollectionSheet")
    suspend fun submitProductiveSheet(
        @Path("centerId") centerId: Int,
        @Body payload: ProductiveCollectionSheetPayload
    ): ApiResponse<GenericResponse>

    @POST("groups/{groupId}?command=generateCollectionSheet")
    suspend fun fetchCollectionSheet(
        @Path("groupId") groupId: Int,
        @Body payload: CollectionSheetRequestPayload
    ): ApiResponse<CollectionSheetResponse>

    @POST("groups/{groupId}?command=saveCollectionSheet")
    suspend fun submitCollectionSheet(
        @Path("groupId") groupId: Int, @Body payload: CollectionSheetPayload
    ): ApiResponse<GenericResponse>

    @GET("centers/{centerId}?associations=groupMembers, collectionMeetingCalendar")
    suspend fun fetchGroupsAssociatedWithCenter(
        @Path("centerId") centerId: Int
    ): ApiResponse<CenterWithAssociations>
}