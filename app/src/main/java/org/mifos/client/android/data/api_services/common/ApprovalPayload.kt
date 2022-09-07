package org.mifos.client.android.data.api_services.common


import org.mifos.client.android.data.Consts
import kotlinx.serialization.Serializable

@Serializable
data class ApprovalPayload(
    val locale: String = Consts.API_LOCALE,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val approvedOnDate: String
)