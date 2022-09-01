package org.mifos.client.core.loans


import kotlinx.serialization.Serializable

@Serializable
data class LoanTransactionTemplate(
    val type: Type,
    val date: List<Int>,
    val amount: Double,
    val manuallyReversed: Boolean,
    val possibleNextRepaymentDate: List<Int>,
    val paymentTypeOptions: List<PaymentTypeOption>
) {
    @Serializable
    data class Type(
        val id: Int,
        val code: String,
        val value: String,
        val disbursement: Boolean,
        val repaymentAtDisbursement: Boolean,
        val repayment: Boolean,
        val contra: Boolean,
        val waiveInterest: Boolean,
        val waiveCharges: Boolean,
        val accrual: Boolean,
        val writeOff: Boolean,
        val recoveryRepayment: Boolean,
        val initiateTransfer: Boolean,
        val approveTransfer: Boolean,
        val withdrawTransfer: Boolean,
        val rejectTransfer: Boolean,
        val chargePayment: Boolean,
        val refund: Boolean,
        val refundForActiveLoans: Boolean
    )

    @Serializable
    data class PaymentTypeOption(
        val id: Int,
        val name: String,
        val description: String,
        val isCashPayment: Boolean,
        val position: Int
    )
}