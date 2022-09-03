package org.mifos.client.android.data.api_services.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthPostRequest(
    val username: String,
    val password: String
)