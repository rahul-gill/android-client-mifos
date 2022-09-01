package org.mifos.client.core.collectionsheet

import org.mifos.client.core.common.Currency
import org.mifos.client.core.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class IndividualCollectionSheet(
    val dueDate: List<Int>,
    val clients: ArrayList<ClientCollectionSheet>,
    val paymentTypeOptions: ArrayList<PaymentTypeOptions>,
) {
    @Serializable
    data class PaymentTypeOptions(
        val id: Int,
        val name: String
    )

    @Serializable
    data class ClientCollectionSheet(
        val clientId: Int,
        val clientName: String,
        val loans: List<LoanCollectionSheet>? = null,
        val attendanceType: AttendanceTypeOption,
        val savings: List<SavingsCollectionSheet>
    ) {
        @Serializable
        data class LoanCollectionSheet(
            val accountId: String,
            val accountStatusId: Int,
            val currency: Currency,
            val interestDue: Double,
            val interestPaid: Double,
            val loanId: Int,
            val principalDue: Double,
            val productId: Double,
            val totalDue: Double,
            val chargesDue: Double,
            val productShortName: String,
        )

        @Serializable
        data class SavingsCollectionSheet(
            val accountId: String,
            val accountStatusId: Int,
            val currency: Currency,
            val depositAccountType: String,
            val dueAmount: Int,
            val productId: Int,
            val productName: String,
            val savingsId: Int
        )

        @JvmInline
        @Serializable
        value class AttendanceTypeOption(val data: EnumOptionData)
    }
}