package org.mifos.client.android.data.api_services.auth

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("authentication")
    suspend fun authenticate(@Body authPostRequest: AuthPostRequest): ApiResponse<AuthPostResponse>
}