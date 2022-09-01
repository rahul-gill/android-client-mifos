package org.mifos.client.core.client

import kotlinx.serialization.Serializable

@Serializable
data class CreateClientIdentifierPayload(
    val documentTypeId: String,
    val documentKey: String,
    val description: String? = null
)