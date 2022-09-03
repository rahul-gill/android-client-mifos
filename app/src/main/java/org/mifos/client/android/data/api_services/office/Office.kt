package org.mifos.client.android.data.api_services.office


import kotlinx.serialization.Serializable

@Serializable
data class Office(
    val id: Int,
    val name: String,
    val nameDecorated: String,
    val externalId: String?,
    val openingDate: List<Int>,
    val hierarchy: String,
    val parentId: Int?,
    val parentName: String?
)
