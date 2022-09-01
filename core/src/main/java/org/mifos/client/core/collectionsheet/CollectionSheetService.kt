package org.mifos.client.core.collectionsheet

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.Consts
import org.mifos.client.core.common.GenericResponse
import de.jensklingenberg.ktorfit.http.*

interface CollectionSheetService {
    @POST("collectionsheet?command=generateCollectionSheet")
    fun getIndividualCollectionSheet(
        @Body payload: GenerateCollectionSheetPayload
    ): ApiResponseFlow<IndividualCollectionSheet>

    @POST("collectionsheet?command=saveCollectionSheet")
    fun saveIndividualCollectionSheet(
        @Body payload: SaveCollectionSheetPayload
    ): ApiResponseFlow<GenericResponse>

    @GET("centers")
    fun fetchCenterDetails(
        @Query("dateFormat") format: String = Consts.API_DATE_FORMAT,
        @Query("locale") locale: String = Consts.API_LOCALE,
        @Query("meetingDate") meetingDate: String,
        @Query("officeId") officeId: Int,
        @Query("staffId") staffId: Int
    ): ApiResponseFlow<List<CenterDetails>>

    @POST("centers/{centerId}?command=generateCollectionSheet")
    fun fetchProductiveSheet(
        @Path("centerId") centerId: Int,
        @Body payload: CollectionSheetRequestPayload
    ): ApiResponseFlow<CollectionSheetResponse>

    @POST("centers/{centerId}?command=saveCollectionSheet")
    fun submitProductiveSheet(
        @Path("centerId") centerId: Int,
        @Body payload: ProductiveCollectionSheetPayload
    ): ApiResponseFlow<GenericResponse>

    @POST("groups/{groupId}?command=generateCollectionSheet")
    fun fetchCollectionSheet(
        @Path("groupId") groupId: Int,
        @Body payload: CollectionSheetRequestPayload
    ): ApiResponseFlow<CollectionSheetResponse>

    @POST("groups/{groupId}?command=saveCollectionSheet")
    fun submitCollectionSheet(
        @Path("groupId") groupId: Int, @Body payload: CollectionSheetPayload
    ): ApiResponseFlow<GenericResponse>

    @GET("centers/{centerId}?associations=groupMembers, collectionMeetingCalendar")
    fun fetchGroupsAssociatedWithCenter(
        @Path("centerId") centerId: Int
    ): ApiResponseFlow<CenterWithAssociations>
}