package org.mifos.client.core.staff

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface StaffService {
    @GET("staff?status=all")
    suspend fun getStaffForOffice(@Query("officeId") officeId: Int): ApiResponse<List<Staff>>

    @GET("staff")
    suspend fun getAllStaff(): ApiResponse<List<Staff>>

}