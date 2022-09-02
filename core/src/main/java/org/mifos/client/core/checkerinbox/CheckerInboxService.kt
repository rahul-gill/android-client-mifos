package org.mifos.client.core.checkerinbox

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.core.common.GenericResponse
import retrofit2.http.*


interface CheckerInboxService {

    @GET("makercheckers")
    suspend fun getCheckerList() : ApiResponse<List<CheckerTask>>

    @POST("makercheckers/{auditId}?command=approve")
    suspend fun approveCheckerEntry(@Path("auditId") auditId: Int): ApiResponse<GenericResponse>

    @POST("makercheckers/{auditId}?command=reject")
    suspend fun rejectCheckerEntry(@Path("auditId") auditId: Int): ApiResponse<GenericResponse>

    @DELETE("makercheckers/{auditId}")
    suspend fun deleteCheckerEntry(@Path("auditId") auditId: Int): ApiResponse<GenericResponse>

    @GET("rescheduleloans?command=pending")
    suspend fun getRescheduleLoansTaskList(): ApiResponse<List<RescheduleLoansTask>>

    @GET("makercheckers/searchtemplate?fields=entityNames,actionNames")
    suspend fun getCheckerInboxSearchTempalate(): ApiResponse<CheckerInboxSearchTemplate>

    @GET("makercheckers")
    suspend fun getCheckerTasksFromResourceId(
        @Query("actionName") actionName: String? = null,
        @Query("entityName") entityName: String? = null,
        @Query("resourceId") resourceId: Int? = null
    ): ApiResponse<List<CheckerTask>>

}