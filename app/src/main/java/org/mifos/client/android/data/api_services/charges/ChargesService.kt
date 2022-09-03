package org.mifos.client.android.data.api_services.charges

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.android.data.api_services.common.GenericResponse
import org.mifos.client.android.data.api_services.common.Page
import retrofit2.http.*


interface ChargesService {

    @GET("clients/{clientId}/charges/template")
    suspend fun getAllClientCharges(@Path("clientId") clientId: Int): ApiResponse<ClientChargeTemplate>


    @GET("clients/{clientId}/charges")
    suspend fun getListOfCharges(
        @Path("clientId") clientId: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<Page<Charge>>

    @POST("clients/{clientId}/charges")
    suspend fun createCharges(
        @Path("clientId") clientId: Int,
        @Body chargesPayload: CreateClientChargePayload
    ): ApiResponse<GenericResponse>

}
