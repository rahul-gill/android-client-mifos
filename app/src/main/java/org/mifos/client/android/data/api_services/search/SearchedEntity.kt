package org.mifos.client.android.data.api_services.search


import org.mifos.client.android.data.api_services.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class SearchedEntity(
    val entityId: Int,
    val entityAccountNo: String,
    val entityName: String,
    val entityType: String,
    val parentId: Int,
    val parentName: String,
    val entityMobileNo: String? = null,
    val entityStatus: EntityStatus,
    val entityExternalId: String? = null,
    val parentType: String? = null
) {
    @JvmInline
    @Serializable
    value class EntityStatus(val data: EnumOptionData)
}