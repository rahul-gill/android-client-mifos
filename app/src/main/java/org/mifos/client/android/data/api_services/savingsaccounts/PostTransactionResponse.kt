package org.mifos.client.android.data.api_services.savingsaccounts


import kotlinx.serialization.Serializable

@Serializable
data class PostTransactionResponse(
    val officeId: Int,
    val clientId: Int,
    val savingsId: Int,
    val resourceId: Int,
    val changes: Changes
) {
    @Serializable
    data class Changes(
        val accountNumber: String,
        val checkNumber: String,
        val routingCode: String,
        val receiptNumber: String,
        val bankNumber: String
    )
}