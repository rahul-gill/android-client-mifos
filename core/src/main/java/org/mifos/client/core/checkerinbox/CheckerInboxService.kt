package org.mifos.client.core.checkerinbox

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.common.GenericResponse
import de.jensklingenberg.ktorfit.http.*


interface CheckerInboxService {

    @GET("makercheckers")
    fun getCheckerList() : ApiResponseFlow<List<CheckerTask>>

    @POST("makercheckers/{auditId}?command=approve")
    fun approveCheckerEntry(@Path("auditId") auditId: Int): ApiResponseFlow<GenericResponse>

    @POST("makercheckers/{auditId}?command=reject")
    fun rejectCheckerEntry(@Path("auditId") auditId: Int): ApiResponseFlow<GenericResponse>

    @DELETE("makercheckers/{auditId}")
    fun deleteCheckerEntry(@Path("auditId") auditId: Int): ApiResponseFlow<GenericResponse>

    @GET("rescheduleloans?command=pending")
    fun getRescheduleLoansTaskList(): ApiResponseFlow<List<RescheduleLoansTask>>

    @GET("makercheckers/searchtemplate?fields=entityNames,actionNames")
    fun getCheckerInboxSearchTempalate(): ApiResponseFlow<CheckerInboxSearchTemplate>

    @GET("makercheckers")
    fun getCheckerTasksFromResourceId(
        @Query("actionName") actionName: String? = null,
        @Query("entityName") entityName: String? = null,
        @Query("resourceId") resourceId: Int? = null
    ): ApiResponseFlow<List<CheckerTask>>

}