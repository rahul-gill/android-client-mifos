package org.mifos.client.core.datatables

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.common.GenericResponse
import de.jensklingenberg.ktorfit.http.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject


/**
 * @author fomenkoo
 */
interface DataTableService {
    @GET("datatables")
    fun getTableOf(@Query("apptable") table: String): ApiResponseFlow<List<DataTable>>

    @GET("datatables/{dataTableName}/{entityId}/")
    fun getDataOfDataTable(
        @Path("dataTableName") dataTableName: String,
        @Path("entityId") entityId: Int
    ): ApiResponseFlow<JsonArray>

    @POST("datatables/{dataTableName}/{entityId}/")
    fun createEntryInDataTable(
        @Path("dataTableName") dataTableName: String,
        @Path("entityId") entityId: Int,
        @Body requestPayload: Map<String, Any>
    ): ApiResponseFlow<GenericResponse>

    @DELETE("datatables/{dataTableName}/{entityId}/{dataTableRowId}")
    fun deleteEntryOfDataTableManyToMany(
        @Path("dataTableName") dataTableName: String,
        @Path("entityId") entityId: Int,
        @Path("dataTableRowId") dataTableRowId: Int
    ): ApiResponseFlow<GenericResponse>

    @POST("datatables/user_tracking/{userId}")
    fun addUserPathTracking(
        @Path("userId") userId: Int,
        @Body userLocation: UserLocation
    ): ApiResponseFlow<GenericResponse>

    @GET("datatables/user_tracking/{userId}")
    fun getUserPathTracking(@Path("userId") userId: Int): ApiResponseFlow<List<UserLocation>>
}

fun processDataTableData(data: JsonArray) =  data.map { jsonElement ->
    jsonElement.jsonObject.entries.associate {  entry ->
        entry.key to entry.value.toString()
    }
}

