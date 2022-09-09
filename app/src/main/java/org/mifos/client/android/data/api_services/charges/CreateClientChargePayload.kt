package org.mifos.client.android.data.api_services.charges


import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class CreateClientChargePayload(
    val amount: String,
    val chargeId: String,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val dueDate: String,
    val locale: String = ApiDefaults.API_LOCALE
)