package org.mifos.client.android.data.api_services.datatables

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.android.data.api_services.common.GenericResponse
import retrofit2.http.*


/**
 * @author fomenkoo
 */
interface DataTableService {
    @GET("datatables")
    suspend fun getTableOf(@Query("apptable") table: String): ApiResponse<List<DataTable>>

    /**
     * TODO: what return type to use return type
     */
    @GET("datatables/{dataTableName}/{entityId}/")
    suspend fun getDataOfDataTable(
        @Path("dataTableName") dataTableName: String,
        @Path("entityId") entityId: Int
    ): ApiResponse<String>

    @POST("datatables/{dataTableName}/{entityId}/")
    suspend fun createEntryInDataTable(
        @Path("dataTableName") dataTableName: String,
        @Path("entityId") entityId: Int,
        @Body requestPayload: Map<String, Any>
    ): ApiResponse<GenericResponse>

    @DELETE("datatables/{dataTableName}/{entityId}/{dataTableRowId}")
    suspend fun deleteEntryOfDataTableManyToMany(
        @Path("dataTableName") dataTableName: String,
        @Path("entityId") entityId: Int,
        @Path("dataTableRowId") dataTableRowId: Int
    ): ApiResponse<GenericResponse>

    @POST("datatables/user_tracking/{userId}")
    suspend fun addUserPathTracking(
        @Path("userId") userId: Int,
        @Body userLocation: UserLocation
    ): ApiResponse<GenericResponse>

    @GET("datatables/user_tracking/{userId}")
    suspend fun getUserPathTracking(@Path("userId") userId: Int): ApiResponse<List<UserLocation>>
}

