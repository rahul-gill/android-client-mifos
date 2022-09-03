package org.mifos.client.android.data.api_services.centers


import kotlinx.serialization.Serializable

@Serializable
data class SaveCollectionSheetPayload(
    val dateFormat: String,
    val locale: String,
    val calendarId: Int,
    val transactionDate: String,
    val actualDisbursementDate: String,
    val bulkRepaymentTransactions: List<BulkRepaymentTransaction>
) {
    @Serializable
    data class BulkRepaymentTransaction(
        val loanId: Int,
        val transactionAmount: Double
    )
}