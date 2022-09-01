package org.mifos.client.core.groups


import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class ActivateGroupPayload(
    val activationDate: String,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE
)