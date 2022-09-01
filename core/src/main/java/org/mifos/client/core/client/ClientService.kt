package org.mifos.client.core.client

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.common.ActivationPayload
import org.mifos.client.core.common.GenericResponse
import org.mifos.client.core.common.Page
import de.jensklingenberg.ktorfit.http.*

interface ClientService {
    @GET("clients")
    fun getAllClients(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("paged") paged: Boolean = true,
    ): ApiResponseFlow<Page<Client>>

    @GET("clients/{clientId}")
    fun getClient(@Path("clientId") clientId: Int): ApiResponseFlow<Client>

//    @Multipart
//    @POST("clients/{clientId}/images")
//    fun uploadClientImage(
//        @Path("clientId") clientId: Int,
//        @Part file: Part?
//    ): ApiResponseFlow<ResponseBody>

//    @DELETE("clients/{clientId}/images")
//    fun deleteClientImage(@Path("clientId") clientId: Int): ApiResponseFlow<ResponseBody>

    @POST("clients")
    fun createClient(@Body clientPayload: CreateClientPayload): ApiResponseFlow<Client>

    @GET("clients/template")
    fun getClientTemplate(): ApiResponseFlow<ClientTemplate>

    @GET("clients/{clientId}/accounts")
    fun getClientAccounts(@Path("clientId") clientId: Int): ApiResponseFlow<ClientAccountsResponse>

    @GET("clients/{clientId}/identifiers")
    fun getClientIdentifiers(@Path("clientId") clientId: Int): ApiResponseFlow<List<ClientIdentifier>>

    @POST("clients/{clientId}/identifiers")
    fun createClientIdentifier(
        @Path("clientId") clientId: Int,
        @Body identifierPayload: CreateClientIdentifierPayload
    ): ApiResponseFlow<GenericResponse>

    @GET("clients/{clientId}/identifiers/template")
    fun getClientIdentifierTemplate(@Path("clientId") clientId: Int): ApiResponseFlow<ClientIdentifierTemplate>

    @DELETE("clients/{clientId}/identifiers/{identifierId}")
    fun deleteClientIdentifier(
        @Path("clientId") clientId: Int,
        @Path("identifierId") identifierId: Int
    ): ApiResponseFlow<GenericResponse>


    @POST("clients/{clientId}?command=activate")
    fun activateClient(
        @Path("clientId") clientId: Int,
        @Body clientActivate: ActivationPayload
    ): ApiResponseFlow<GenericResponse>
}
