package org.mifos.client.android.data.api_services.common


import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class ApprovalPayload(
    val locale: String = ApiDefaults.API_LOCALE,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val approvedOnDate: String
)