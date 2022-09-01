package org.mifos.client.core.collectionsheet

import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSheetPayload(
    private val actualDisbursementDate: String,
    private val bulkRepaymentTransactions: List<ProductiveCollectionSheetPayload.BulkRepaymentTransactions>,
    private val bulkSavingsDueTransactions: List<BulkSavingsDueTransaction>,
    private val calendarId: Int,
    private val clientsAttendance: List<ClientsAttendance>,
    private val dateFormat: String = Consts.API_DATE_FORMAT,
    private val locale: String = Consts.API_LOCALE,
    private val transactionDate: String,
    private val accountNumber: String,
    private val bankNumber: String,
    private val checkNumber: String,
    private val paymentTypeId: Int,
    private val receiptNumber: String,
    private val routingCode: String,
) {
    @Serializable
    data class BulkSavingsDueTransaction(
        private val savingsId: Int,
        private val transactionAmount: String
    )
    @Serializable
    data class ClientsAttendance(
        private val attendanceType: Int,
        private val clientId: Int
    )
}