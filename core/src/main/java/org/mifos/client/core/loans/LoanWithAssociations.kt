package org.mifos.client.core.loans


import kotlinx.serialization.Serializable

@Serializable
data class LoanWithAssociations(
    val id: Int,
    val accountNo: String,
    val status: Status,
    val clientName: String,
    val loanProductName: String,
    val isLoanProductLinkedToFloatingRate: Boolean,
    val loanType: LoanType,
    val currency: Currency,
    val principal: Double,
    val approvedPrincipal: Double,
    val proposedPrincipal: Double,
    val numberOfRepayments: Int,
    val repaymentEvery: Int,
    val repaymentFrequencyType: RepaymentFrequencyType,
    val interestRatePerPeriod: Double,
    val interestRateFrequencyType: InterestRateFrequencyType,
    val isFloatingInterestRate: Boolean,
    val amortizationType: AmortizationType,
    val interestType: InterestType,
    val interestCalculationPeriodType: InterestCalculationPeriodType,
    val allowPartialPeriodInterestCalcualtion: Boolean,
    val transactionProcessingStrategyId: Int,
    val timeline: Timeline,
    val summary: Summary,
    val repaymentSchedule: RepaymentSchedule,
    val transactions: List<Transaction>,
    val charges: List<Charge>,
    val canDefineInstallmentAmount: Boolean,
    val canDisburse: Boolean,
    val canUseForTopup: Boolean,
    val isTopup: Boolean,
    val closureLoanId: Int,
    val inArrears: Boolean,
    val daysInMonthType: DaysInMonthType,
    val daysInYearType: DaysInYearType,
    val isInterestRecalculationEnabled: Boolean,
    val createStandingInstructionAtDisbursement: Boolean,
    val paidInAdvance: PaidInAdvance,
    val isVariableInstallmentsAllowed: Boolean,
    val minimumGap: Int,
    val maximumGap: Int,
    val isEqualAmortization: Boolean,
    val isRatesEnabled: Boolean
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

    @Serializable
    data class LoanType(
        val id: Int,
        val code: String,
        val value: String
    )

    @Serializable
    data class Currency(
        val code: String,
        val name: String,
        val decimalPlaces: Int,
        val inMultiplesOf: Int,
        val displaySymbol: String,
        val nameCode: String,
        val displayLabel: String
    )

    @Serializable
    data class TermPeriodFrequencyType(
        val id: Int,
        val code: String,
        val value: String
    )

    @Serializable
    data class RepaymentFrequencyType(
        val id: Int,
        val code: String,
        val value: String
    )

    @Serializable
    data class InterestRateFrequencyType(
        val id: Int,
        val code: String,
        val value: String
    )

    @Serializable
    data class AmortizationType(
        val id: Int,
        val code: String,
        val value: String
    )

    @Serializable
    data class InterestType(
        val id: Int,
        val code: String,
        val value: String
    )

    @Serializable
    data class InterestCalculationPeriodType(
        val id: Int,
        val code: String,
        val value: String
    )

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

    @Serializable
    data class Summary(
        val currency: Currency,
        val principalDisbursed: Double,
        val principalPaid: Double,
        val principalWrittenOff: Double,
        val principalOutstanding: Double,
        val principalOverdue: Int,
        val interestCharged: Double,
        val interestPaid: Double,
        val interestWaived: Double,
        val interestWrittenOff: Double,
        val interestOutstanding: Double,
        val interestOverdue: Int,
        val feeChargesCharged: Double,
        val feeChargesDueAtDisbursementCharged: Double,
        val feeChargesPaid: Double,
        val feeChargesWaived: Double,
        val feeChargesWrittenOff: Double,
        val feeChargesOutstanding: Double,
        val feeChargesOverdue: Int,
        val penaltyChargesCharged: Double,
        val penaltyChargesPaid: Double,
        val penaltyChargesWaived: Double,
        val penaltyChargesWrittenOff: Double,
        val penaltyChargesOutstanding: Double,
        val penaltyChargesOverdue: Int,
        val totalExpectedRepayment: Double,
        val totalRepayment: Double,
        val totalExpectedCostOfLoan: Double,
        val totalCostOfLoan: Double,
        val totalWaived: Double,
        val totalWrittenOff: Double,
        val totalOutstanding: Double,
        val totalOverdue: Int
    ) {
        @Serializable
        data class Currency(
            val code: String,
            val name: String,
            val decimalPlaces: Int,
            val inMultiplesOf: Int,
            val displaySymbol: String,
            val nameCode: String,
            val displayLabel: String
        )
    }

    @Serializable
    data class RepaymentSchedule(
        val currency: Currency,
        val loanTermInDays: Int,
        val totalPrincipalDisbursed: Double,
        val totalPrincipalExpected: Double,
        val totalPrincipalPaid: Double,
        val totalInterestCharged: Double,
        val totalFeeChargesCharged: Double,
        val totalPenaltyChargesCharged: Double,
        val totalWaived: Double,
        val totalWrittenOff: Double,
        val totalRepaymentExpected: Double,
        val totalRepayment: Double,
        val totalPaidInAdvance: Double,
        val totalPaidLate: Double,
        val totalOutstanding: Double,
        val periods: List<Period>
    ) {
        @Serializable
        data class Currency(
            val code: String,
            val name: String,
            val decimalPlaces: Int,
            val inMultiplesOf: Int,
            val displaySymbol: String,
            val nameCode: String,
            val displayLabel: String
        )

        @Serializable
        data class Period(
            val dueDate: List<Int>,
            val principalDisbursed: Double?,
            val principalLoanBalanceOutstanding: Double,
            val feeChargesDue: Double,
            val feeChargesPaid: Double,
            val totalOriginalDueForPeriod: Double,
            val totalDueForPeriod: Double,
            val totalPaidForPeriod: Double,
            val totalActualCostOfLoanForPeriod: Double,
            val period: Int?,
            val fromDate: List<Int>?,
            val obligationsMetOnDate: List<Int>?,
            val complete: Boolean?,
            val daysInPeriod: Int?,
            val principalOriginalDue: Double?,
            val principalDue: Double?,
            val principalPaid: Double?,
            val principalWrittenOff: Int?,
            val principalOutstanding: Double?,
            val interestOriginalDue: Double?,
            val interestDue: Double?,
            val interestPaid: Double?,
            val interestWaived: Int?,
            val interestWrittenOff: Int?,
            val interestOutstanding: Double?,
            val feeChargesWaived: Int?,
            val feeChargesWrittenOff: Int?,
            val feeChargesOutstanding: Double?,
            val penaltyChargesDue: Int?,
            val penaltyChargesPaid: Int?,
            val penaltyChargesWaived: Int?,
            val penaltyChargesWrittenOff: Int?,
            val penaltyChargesOutstanding: Int?,
            val totalPaidInAdvanceForPeriod: Int?,
            val totalPaidLateForPeriod: Double?,
            val totalWaivedForPeriod: Int?,
            val totalWrittenOffForPeriod: Int?,
            val totalOutstandingForPeriod: Double?,
            val totalOverdue: Double?,
            val totalInstallmentAmountForPeriod: Double?
        )
    }

    @Serializable
    data class Transaction(
        val id: Int,
        val officeId: Int,
        val officeName: String,
        val type: Type,
        val date: List<Int>,
        val currency: Currency,
        val amount: Double,
        val principalPortion: Double,
        val interestPortion: Double,
        val feeChargesPortion: Double,
        val penaltyChargesPortion: Double,
        val overpaymentPortion: Double,
        val unrecognizedIncomePortion: Int,
        val outstandingLoanBalance: Double,
        val submittedOnDate: List<Int>,
        val manuallyReversed: Boolean,
        val transfer: Transfer?,
        val paymentDetailData: PaymentDetailData?
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
        data class Currency(
            val code: String,
            val name: String,
            val decimalPlaces: Int,
            val inMultiplesOf: Int,
            val displaySymbol: String,
            val nameCode: String,
            val displayLabel: String
        )

        @Serializable
        data class Transfer(
            val id: Int,
            val reversed: Boolean,
            val currency: Currency,
            val transferAmount: Double,
            val transferDate: List<Int>,
            val transferDescription: String
        ) {
            @Serializable
            data class Currency(
                val code: String,
                val name: String,
                val decimalPlaces: Int,
                val inMultiplesOf: Int,
                val displaySymbol: String,
                val nameCode: String,
                val displayLabel: String
            )
        }

        @Serializable
        data class PaymentDetailData(
            val id: Int,
            val paymentType: PaymentType,
            val accountNumber: String,
            val checkNumber: String,
            val routingCode: String,
            val receiptNumber: String,
            val bankNumber: String
        ) {
            @Serializable
            data class PaymentType(
                val id: Int,
                val name: String
            )
        }
    }

    @Serializable
    data class Charge(
        val id: Int,
        val chargeId: Int,
        val name: String,
        val chargeTimeType: ChargeTimeType,
        val chargeCalculationType: ChargeCalculationType,
        val percentage: Int,
        val amountPercentageAppliedTo: Int,
        val currency: Currency,
        val amount: Double,
        val amountPaid: Double,
        val amountWaived: Int,
        val amountWrittenOff: Int,
        val amountOutstanding: Double,
        val amountOrPercentage: Double,
        val penalty: Boolean,
        val chargePaymentMode: ChargePaymentMode,
        val paid: Boolean,
        val waived: Boolean,
        val chargePayable: Boolean
    ) {
        @Serializable
        data class ChargeTimeType(
            val id: Int,
            val code: String,
            val value: String
        )

        @Serializable
        data class ChargeCalculationType(
            val id: Int,
            val code: String,
            val value: String
        )

        @Serializable
        data class Currency(
            val code: String,
            val name: String,
            val decimalPlaces: Int,
            val displaySymbol: String,
            val nameCode: String,
            val displayLabel: String
        )

        @Serializable
        data class ChargePaymentMode(
            val id: Int,
            val code: String,
            val value: String
        )
    }

    @Serializable
    data class DaysInMonthType(
        val id: Int,
        val code: String,
        val value: String
    )

    @Serializable
    data class DaysInYearType(
        val id: Int,
        val code: String,
        val value: String
    )

    @Serializable
    data class PaidInAdvance(
        val paidInAdvance: Int
    )
}