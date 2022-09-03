package org.mifos.client.android.data.api_services.savingsaccounts


import kotlinx.serialization.Serializable

@Serializable
data class CreateSavingsAccountResponse(
    val officeId: Int,
    val clientId: Int,
    val savingsId: Int,
    val resourceId: Int,
    val gsimId: Int
)