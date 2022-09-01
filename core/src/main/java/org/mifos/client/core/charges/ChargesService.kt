package org.mifos.client.core.charges

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.common.GenericResponse
import org.mifos.client.core.common.Page
import de.jensklingenberg.ktorfit.http.*


interface ChargesService {

    @GET("clients/{clientId}/charges/template")
    fun getAllClientCharges(@Path("clientId") clientId: Int): ApiResponseFlow<ClientChargeTemplate>


    @GET("clients/{clientId}/charges")
    fun getListOfCharges(
        @Path("clientId") clientId: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponseFlow<Page<Charge>>

    @POST("clients/{clientId}/charges")
    fun createCharges(
        @Path("clientId") clientId: Int,
        @Body chargesPayload: CreateClientChargePayload
    ): ApiResponseFlow<GenericResponse>

}
