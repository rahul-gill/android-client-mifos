package org.mifos.client.android.data.api_services.loans

import org.mifos.client.android.data.api_services.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class LoanAccount(
    val id: Int,
    val accountNo: String,
    val productId: Int,
    val productName: String,
    val shortProductName: String,
    val status: Status,
    val loanType: LoanType,
    val loanCycle: Int,
    val timeline: Timeline,
    val inArrears: Boolean,
    val originalLoan: Double,
    val loanBalance: Double,
    val amountPaid: Double
) {
    @Serializable
    data class Status(
        val id: Int,
        val code: String,
        val value: String,
        val pendingApproval: Boolean,
        val waitingForDisbursal: Boolean,
        val active: Boolean,
        val closedObligationsMet: Boolean,
        val closedWrittenOff: Boolean,
        val closedRescheduled: Boolean,
        val closed: Boolean,
        val overpaid: Boolean
    )


    @JvmInline
    @Serializable
    value class LoanType(val data: EnumOptionData)

    @Serializable
    data class Timeline(
        val submittedOnDate: List<Int>,
        val submittedByUsername: String,
        val submittedByFirstname: String,
        val submittedByLastname: String,
        val approvedOnDate: List<Int>,
        val approvedByUsername: String,
        val approvedByFirstname: String,
        val approvedByLastname: String,
        val expectedDisbursementDate: List<Int>,
        val actualDisbursementDate: List<Int>,
        val disbursedByUsername: String,
        val disbursedByFirstname: String,
        val disbursedByLastname: String,
        val expectedMaturityDate: List<Int>
    )
}