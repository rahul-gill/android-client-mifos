package org.mifos.client.android.data.api_services.documents


import kotlinx.serialization.Serializable

@Serializable
data class Document(
    val id: Int,
    val parentEntityType: String,
    val parentEntityId: Int,
    val name: String,
    val fileName: String,
    val size: Int,
    val type: String,
    val description: String
)