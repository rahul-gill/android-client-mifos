package org.mifos.client.android.data.api_services.groups


import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class CreateGroupPayload(
    val name: String,
    val officeId: Int,
    val submittedOnDate: String,
    val externalId: String = "",
    val active: Boolean = false,
    val activationDate: String? = null,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val locale: String = ApiDefaults.API_LOCALE,
    //TODO
//    val clientMembers: List<Any>
)