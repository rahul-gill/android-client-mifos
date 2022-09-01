package org.mifos.client.core.auth

import org.mifos.client.core.ApiResponseFlow
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface AuthService {

    @POST("/authentication")
    fun authenticate(@Body authPostRequest: AuthPostRequest): ApiResponseFlow<AuthPostResponse>
}