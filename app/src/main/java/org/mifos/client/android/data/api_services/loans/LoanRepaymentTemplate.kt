package org.mifos.client.android.data.api_services.loans

import org.mifos.client.android.data.api_services.common.Currency
import kotlinx.serialization.Serializable

@Serializable
data class LoanRepaymentTemplate(
    val loanId: Int,
    val type: Type,
    val date: List<Int>,
    val currency: Currency,
    val amount: Double,
    val principalPortion: Double,
    val interestPortion: Double,
    val feeChargesPortion: Double,
    val penaltyChargesPortion: Double,
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
    )

    @Serializable
    data class PaymentTypeOption(
        val id: Int,
        val name: String,
        val description: String,
        val isCashPayment: Boolean,
        val position: Int,
    )
}