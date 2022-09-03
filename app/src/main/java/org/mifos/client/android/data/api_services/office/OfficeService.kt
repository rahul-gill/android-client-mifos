package org.mifos.client.android.data.api_services.office

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface OfficeService {

    @GET("offices")
    suspend fun getAllOffices(): ApiResponse<List<Office>>
}
