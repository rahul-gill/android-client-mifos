package org.mifos.client.core.savingsaccounts


import org.mifos.client.core.common.Currency
import org.mifos.client.core.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class SavingsAccountWithTransactions(
    val id: Int,
    val accountNo: String,
    val depositType: SavingsAccount.DepositType,
    val clientId: Int,
    val clientName: String,
    val savingsProductId: Int,
    val savingsProductName: String,
    val fieldOfficerId: Int,
    val fieldOfficerName: String,
    val status: SavingsAccount.Status,
    val subStatus: SavingsAccount.SubStatus,
    val timeline: SavingsAccount.Timeline,
    val currency: Currency,
    val nominalAnnualInterestRate: Double,
    val interestCompoundingPeriodType: InterestCompoundingPeriodType,
    val interestPostingPeriodType: InterestPostingPeriodType,
    val interestCalculationType: InterestCalculationType,
    val interestCalculationDaysInYearType: InterestCalculationDaysInYearType,
    val minRequiredOpeningBalance: Double,
    val withdrawalFeeForTransfers: Boolean,
    val allowOverdraft: Boolean,
    val minRequiredBalance: Double,
    val enforceMinRequiredBalance: Boolean,
    val withHoldTax: Boolean,
    val lastActiveTransactionDate: List<Int>,
    val isDormancyTrackingActive: Boolean,
    val summary: Summary,
    val transactions: List<Transaction>
) {

    @JvmInline
    @Serializable
    value class InterestCompoundingPeriodType(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class InterestPostingPeriodType(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class InterestCalculationType(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class InterestCalculationDaysInYearType(val data: EnumOptionData)


    @Serializable
    data class Summary(
        val currency: Currency,
        val totalDeposits: Double,
        val totalWithdrawals: Double,
        val totalInterestEarned: Double,
        val totalInterestPosted: Double,
        val accountBalance: Double,
        val totalFeeCharge: Double,
        val totalOverdraftInterestDerived: Int,
        val interestNotPosted: Double,
        val lastInterestCalculationDate: List<Int>,
        val availableBalance: Double
    )

    @Serializable
    data class Transaction(
        val id: Int,
        val transactionType: TransactionType,
        val accountId: Int,
        val accountNo: String,
        val date: List<Int>,
        val currency: Currency,
        val amount: Double,
        val runningBalance: Double,
        val reversed: Boolean,
        val submittedOnDate: List<Int>,
        val interestedPostedAsOn: Boolean,
        val transfer: Transfer?,
        val submittedByUsername: String?,
        val paymentDetailData: PaymentDetailData?
    ) {
        @Serializable
        data class TransactionType(
            val id: Int,
            val code: String,
            val value: String,
            val deposit: Boolean,
            val dividendPayout: Boolean,
            val withdrawal: Boolean,
            val interestPosting: Boolean,
            val feeDeduction: Boolean,
            val initiateTransfer: Boolean,
            val approveTransfer: Boolean,
            val withdrawTransfer: Boolean,
            val rejectTransfer: Boolean,
            val overdraftInterest: Boolean,
            val writtenoff: Boolean,
            val overdraftFee: Boolean,
            val withholdTax: Boolean,
            val escheat: Boolean,
            val amountHold: Boolean,
            val amountRelease: Boolean
        )

        @Serializable
        data class Transfer(
            val id: Int,
            val reversed: Boolean,
            val currency: Currency,
            val transferAmount: Double,
            val transferDate: List<Int>,
            val transferDescription: String
        )

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
            @JvmInline
            @Serializable
            value class PaymentType(val data: EnumOptionData)
        }
    }
}