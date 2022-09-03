package org.mifos.client.android.data.api_services.centers


import kotlinx.serialization.Serializable

@Serializable
data class SaveCollectionSheetResponse(
    val groupId: Int,
    val resourceId: Int,
    val changes: Changes
) {
    @Serializable
    data class Changes(
        val locale: String,
        val dateFormat: String,
    )
}