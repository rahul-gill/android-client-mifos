package org.mifos.client.core.client


import kotlinx.serialization.Serializable

@Serializable
data class ClientIdentifierTemplate(
    val allowedDocumentTypes: List<AllowedDocumentType>
) {
    @Serializable
    data class AllowedDocumentType(
        val id: Int,
        val name: String,
        val position: Int,
        val active: Boolean,
        val mandatory: Boolean,
        val description: String?
    )
}