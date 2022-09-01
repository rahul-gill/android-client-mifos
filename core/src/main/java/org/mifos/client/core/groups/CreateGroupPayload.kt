package org.mifos.client.core.groups


import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class CreateGroupPayload(
    val name: String,
    val officeId: Int,
    val submittedOnDate: String,
    val externalId: String = "",
    val active: Boolean = false,
    val activationDate: String? = null,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    //TODO
//    val clientMembers: List<Any>
)