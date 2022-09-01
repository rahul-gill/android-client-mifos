package org.mifos.client.core.common


import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class ApprovalPayload(
    val locale: String = Consts.API_LOCALE,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val approvedOnDate: String
)