package org.mifos.client.android.data.api_services.client

import kotlinx.serialization.Serializable

@Serializable
data class CreateClientIdentifierPayload(
    val documentTypeId: String,
    val documentKey: String,
    val description: String? = null
)