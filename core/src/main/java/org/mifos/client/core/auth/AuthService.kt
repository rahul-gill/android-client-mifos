package org.mifos.client.core.auth

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("authentication")
    suspend fun authenticate(@Body authPostRequest: AuthPostRequest): ApiResponse<AuthPostResponse>
}