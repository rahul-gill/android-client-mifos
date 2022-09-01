package org.mifos.client.core.office


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfficeItem(
    val id: Int,
    val name: String,
    val nameDecorated: String,
    val externalId: String?,
    val openingDate: List<Int>,
    val hierarchy: String,
    val parentId: Int?,
    val parentName: String?
)
