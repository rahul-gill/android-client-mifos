package org.mifos.client.core.staff

import org.mifos.client.core.ApiResponseFlow
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query


interface StaffService {
    @GET("staff?status=all")
    fun getStaffForOffice(@Query("officeId") officeId: Int): ApiResponseFlow<List<Staff>>

    @GET("staff")
    fun getAllStaff(): ApiResponseFlow<List<Staff>>

}