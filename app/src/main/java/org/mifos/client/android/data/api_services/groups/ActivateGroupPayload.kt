package org.mifos.client.android.data.api_services.groups


import org.mifos.client.android.data.Consts
import kotlinx.serialization.Serializable

@Serializable
data class ActivateGroupPayload(
    val activationDate: String,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE
)