package org.mifos.client.core.loans


import kotlinx.serialization.Serializable

@Serializable
data class LoanProduct(
    val id: Int,
    val name: String,
    val shortName: String,
    val includeInBorrowerCycle: Boolean,
    val useBorrowerCycle: Boolean,
    val status: String,
    val currency: Currency,
    val principal: Double,
    val minPrincipal: Double?,
    val maxPrincipal: Double?,
    val numberOfRepayments: Int,
    val minNumberOfRepayments: Int?,
    val maxNumberOfRepayments: Int?,
    val repaymentEvery: Int,
    val repaymentFrequencyType: RepaymentFrequencyType,
    val interestRatePerPeriod: Double,
    val minInterestRatePerPeriod: Double?,
    val maxInterestRatePerPeriod: Double?,
    val interestRateFrequencyType: InterestRateFrequencyType,
    val annualInterestRate: Double,
    val isLinkedToFloatingInterestRates: Boolean,
    val isFloatingInterestRateCalculationAllowed: Boolean,
    val allowVariableInstallments: Boolean,
    val minimumGap: Int,
    val maximumGap: Int,
    val amortizationType: AmortizationType,
    val interestType: InterestType,
    val interestCalculationPeriodType: InterestCalculationPeriodType,
    val allowPartialPeriodInterestCalcualtion: Boolean,
    val transactionProcessingStrategyId: Int,
    val transactionProcessingStrategyName: String,
    val daysInMonthType: DaysInMonthType,
    val daysInYearType: DaysInYearType,
    val isInterestRecalculationEnabled: Boolean,
    val interestRecalculationData: InterestRecalculationData?,
    val canDefineInstallmentAmount: Boolean,
    val installmentAmountInMultiplesOf: Int,
    val accountingRule: AccountingRule,
    val canUseForTopup: Boolean,
    val isRatesEnabled: Boolean,
    val multiDisburseLoan: Boolean,
    val maxTrancheCount: Int,
    val principalThresholdForLastInstallment: Double,
    val holdGuaranteeFunds: Boolean,
    val accountMovesOutOfNPAOnlyOnArrearsCompletion: Boolean,
    val allowAttributeOverrides: AllowAttributeOverrides,
    val syncExpectedWithDisbursementDate: Boolean,
    val isEqualAmortization: Boolean,
    val outstandingLoanBalance: Double?,
    val fundId: Int?,
    val fundName: String?,
    val startDate: List<Int>?,
    val minimumDaysBetweenDisbursalAndFirstRepayment: Int?,
    val description: String?,
    val overdueDaysForNPA: Int?,
    val inArrearsTolerance: Double?,
    val graceOnArrearsAgeing: Int?,
    val closeDate: List<Int>?,
    val graceOnInterestCharged: Int?
) {
    @Serializable
    data class Currency(
        val code: String,
        val name: String,
        val decimalPlaces: Int,
        val inMultiplesOf: Int,
        val displaySymbol: String?,
        val nameCode: String,
        val displayLabel: String
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
    data class InterestRecalculationData(
        val id: Int,
        val productId: Int,
        val interestRecalculationCompoundingType: InterestRecalculationCompoundingType,
        val rescheduleStrategyType: RescheduleStrategyType,
        val recalculationRestFrequencyType: RecalculationRestFrequencyType,
        val recalculationRestFrequencyInterval: Int,
        val isArrearsBasedOnOriginalSchedule: Boolean,
        val isCompoundingToBePostedAsTransaction: Boolean,
        val preClosureInterestCalculationStrategy: PreClosureInterestCalculationStrategy,
        val allowCompoundingOnEod: Boolean
    ) {
        @Serializable
        data class InterestRecalculationCompoundingType(
            val id: Int,
            val code: String,
            val value: String
        )

        @Serializable
        data class RescheduleStrategyType(
            val id: Int,
            val code: String,
            val value: String
        )

        @Serializable
        data class RecalculationRestFrequencyType(
            val id: Int,
            val code: String,
            val value: String
        )

        @Serializable
        data class PreClosureInterestCalculationStrategy(
            val id: Int,
            val code: String,
            val value: String
        )
    }

    @Serializable
    data class AccountingRule(
        val id: Int,
        val code: String,
        val value: String
    )

    @Serializable
    data class AllowAttributeOverrides(
        val amortizationType: Boolean,
        val interestType: Boolean,
        val transactionProcessingStrategyId: Boolean,
        val interestCalculationPeriodType: Boolean,
        val inArrearsTolerance: Boolean,
        val repaymentEvery: Boolean,
        val graceOnPrincipalAndInterestPayment: Boolean,
        val graceOnArrearsAgeing: Boolean
    )
}
