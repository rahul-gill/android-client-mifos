package org.mifos.client.core.charges


import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class CreateClientChargePayload(
    val amount: String,
    val chargeId: String,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val dueDate: String,
    val locale: String = Consts.API_LOCALE
)