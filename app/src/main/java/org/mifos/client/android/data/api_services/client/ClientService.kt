package org.mifos.client.android.data.api_services.client

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.android.data.api_services.common.ActivationPayload
import org.mifos.client.android.data.api_services.common.GenericResponse
import org.mifos.client.android.data.api_services.common.Page
import retrofit2.http.*

interface ClientService {
    @GET("clients")
    suspend fun getAllClients(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("paged") paged: Boolean = true,
    ): Page<Client>

    @GET("clients/{clientId}")
    suspend fun getClient(@Path("clientId") clientId: Int): ApiResponse<Client>

//    @Multipart
//    @POST("clients/{clientId}/images")
//    suspend fun uploadClientImage(
//        @Path("clientId") clientId: Int,
//        @Part file: Part?
//    ): ApiResponse<ResponseBody>

//    @DELETE("clients/{clientId}/images")
//    suspend fun deleteClientImage(@Path("clientId") clientId: Int): ApiResponse<ResponseBody>

    @POST("clients")
    suspend fun createClient(@Body clientPayload: CreateClientPayload): ApiResponse<Client>

    @GET("clients/template")
    suspend fun getClientTemplate(): ApiResponse<ClientTemplate>

    @GET("clients/{clientId}/accounts")
    suspend fun getClientAccounts(@Path("clientId") clientId: Int): ApiResponse<ClientAccountsResponse>

    @GET("clients/{clientId}/identifiers")
    suspend fun getClientIdentifiers(@Path("clientId") clientId: Int): ApiResponse<List<ClientIdentifier>>

    @POST("clients/{clientId}/identifiers")
    suspend fun createClientIdentifier(
        @Path("clientId") clientId: Int,
        @Body identifierPayload: CreateClientIdentifierPayload
    ): ApiResponse<GenericResponse>

    @GET("clients/{clientId}/identifiers/template")
    suspend fun getClientIdentifierTemplate(@Path("clientId") clientId: Int): ApiResponse<ClientIdentifierTemplate>

    @DELETE("clients/{clientId}/identifiers/{identifierId}")
    suspend fun deleteClientIdentifier(
        @Path("clientId") clientId: Int,
        @Path("identifierId") identifierId: Int
    ): ApiResponse<GenericResponse>


    @POST("clients/{clientId}?command=activate")
    suspend fun activateClient(
        @Path("clientId") clientId: Int,
        @Body clientActivate: ActivationPayload
    ): ApiResponse<GenericResponse>
}
