package org.mifos.client.android.data.api_services.groups


import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class ActivateGroupPayload(
    val activationDate: String,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val locale: String = ApiDefaults.API_LOCALE
)