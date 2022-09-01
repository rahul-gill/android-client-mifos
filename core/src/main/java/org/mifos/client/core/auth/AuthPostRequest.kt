package org.mifos.client.core.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthPostRequest(
    val username: String,
    val password: String
)