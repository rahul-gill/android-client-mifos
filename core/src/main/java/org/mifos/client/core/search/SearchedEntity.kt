package org.mifos.client.core.search


import org.mifos.client.core.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class SearchedEntity(
    val entityId: Int,
    val entityAccountNo: String,
    val entityName: String,
    val entityType: String,
    val parentId: Int,
    val parentName: String,
    val entityMobileNo: String?,
    val entityStatus: EntityStatus,
    val entityExternalId: String?,
    val parentType: String?
) {
    @JvmInline
    @Serializable
    value class EntityStatus(val data: EnumOptionData)
}