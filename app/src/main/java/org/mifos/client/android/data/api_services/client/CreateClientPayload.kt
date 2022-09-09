package org.mifos.client.android.data.api_services.client

import kotlinx.serialization.Serializable

@Serializable
data class CreateClientPayload(
    val firstname: String,
    val middlename: String? = null,
    val lastname: String,
    val officeId: Int,
    val legalFormId: Int? = null,
    val dateOfBirth: String? = null,
    val mobileNo: String? = null,
    val emailAddress: String? = null,
    val externalId: String? = null,
    val staffId: String? = null,
    val genderId: String? = null,
    val clientTypeId: String? = null,
    val clientClassificationId: String? = null,
    val dateFormat: String,
    val submittedOnDate: String,
    val locale: String,
    val active: Boolean = false,
    val activationDate: String? = null,
    val isStaff: Boolean = false,
)