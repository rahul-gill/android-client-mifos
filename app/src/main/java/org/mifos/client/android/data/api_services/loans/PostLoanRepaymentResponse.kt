package org.mifos.client.android.data.api_services.loans


import kotlinx.serialization.Serializable

@Serializable
data class PostLoanRepaymentResponse(
    val officeId: Int,
    val clientId: Int,
    val loanId: Int,
    val resourceId: Int,
    val changes: Changes
) {
    @Serializable
    data class Changes(
        val transactionDate: String,
        val transactionAmount: String,
        val locale: String,
        val dateFormat: String,
        val note: String,
        val accountNumber: String,
        val checkNumber: String,
        val routingCode: String,
        val receiptNumber: String,
        val bankNumber: String
    )
}