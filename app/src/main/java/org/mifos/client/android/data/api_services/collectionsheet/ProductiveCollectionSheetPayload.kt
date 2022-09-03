package org.mifos.client.android.data.api_services.collectionsheet

import org.mifos.client.android.data.Consts
import kotlinx.serialization.Serializable

@Serializable
data class ProductiveCollectionSheetPayload(
    val bulkRepaymentTransactions: List<BulkRepaymentTransactions>,
    val calendarId: Int,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    val transactionDate: String
) {
    @Serializable
    data class BulkRepaymentTransactions(
        var loanId: Int,
        var transactionAmount: Double,
        val accountNumber: String? = null,
        val bankNumber: String? = null,
        val checkNumber: String? = null,
        val paymentTypeId: Int? = null,
        val receiptNumber: String? = null,
        val routingCode: String? = null,
    )
}