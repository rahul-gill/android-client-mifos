package org.mifos.client.core.savingsaccounts


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateSavingsAccountResponse(
    val officeId: Int,
    val clientId: Int,
    val savingsId: Int,
    val resourceId: Int,
    val gsimId: Int
)