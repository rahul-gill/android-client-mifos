package org.mifos.client.android.data.api_services.client


import kotlinx.serialization.Serializable

@Serializable
data class ClientIdentifier(
    val id: Int,
    val clientId: Int,
    val documentType: DocumentType,
    val documentKey: String,
    val status: String
) {
    @Serializable
    data class DocumentType(
        val id: Int,
        val name: String,
        val active: Boolean,
        val mandatory: Boolean
    )
}