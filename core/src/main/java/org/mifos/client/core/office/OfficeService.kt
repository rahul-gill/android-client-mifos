package org.mifos.client.core.office

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface OfficeService {

    @GET("offices")
    suspend fun getAllOffices(): ApiResponse<List<Office>>
}
