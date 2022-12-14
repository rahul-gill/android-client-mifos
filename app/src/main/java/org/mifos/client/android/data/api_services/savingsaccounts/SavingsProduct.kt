package org.mifos.client.android.data.api_services.savingsaccounts


import org.mifos.client.android.data.api_services.common.Currency
import org.mifos.client.android.data.api_services.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class SavingsProduct(
    val id: Int,
    val name: String,
    val shortName: String,
    val description: String?,
    val currency: Currency,
    val nominalAnnualInterestRate: Double,
    val interestCompoundingPeriodType: InterestCompoundingPeriodType,
    val interestPostingPeriodType: InterestPostingPeriodType,
    val interestCalculationType: InterestCalculationType,
    val interestCalculationDaysInYearType: InterestCalculationDaysInYearType,
    val withdrawalFeeForTransfers: Boolean,
    val allowOverdraft: Boolean,
    val enforceMinRequiredBalance: Boolean,
    val withHoldTax: Boolean,
    val accountingRule: AccountingRule,
    val isDormancyTrackingActive: Boolean,
    val minRequiredOpeningBalance: Double?,
    val overdraftLimit: Double?,
    val nominalAnnualInterestRateOverdraft: Double?,
    val minOverdraftForInterestCalculation: Double?,
    val minRequiredBalance: Double?,
    val minBalanceForInterestCalculation: Double?
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

    @JvmInline
    @Serializable
    value class AccountingRule(val data: EnumOptionData)

}
