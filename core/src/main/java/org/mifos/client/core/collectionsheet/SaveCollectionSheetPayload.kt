package org.mifos.client.core.collectionsheet


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaveCollectionSheetPayload(
    val dateFormat: String,
    val locale: String,
    val transactionDate: String,
    val actualDisbursementDate: String,
    val bulkDisbursementTransactions: List<Int>,
    val bulkRepaymentTransactions: List<BulkRepaymentTransaction>,
    val bulkSavingsDueTransactions: List<Int>
) {
    @Serializable
    data class BulkRepaymentTransaction(
        val loanId: Int,
        val transactionAmount: Double,
        val paymentTypeId: Int,
        val receiptNumber: String
    )
}