package org.mifos.client.android.data.api_services.savingsaccounts


import org.mifos.client.android.data.api_services.common.Currency
import org.mifos.client.android.data.api_services.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class SavingsProductTemplate(
    val currency: Currency,
    val interestCompoundingPeriodType: SavingsProduct.InterestCompoundingPeriodType,
    val interestPostingPeriodType: SavingsProduct.InterestPostingPeriodType,
    val interestCalculationType: SavingsProduct.InterestCalculationType,
    val interestCalculationDaysInYearType: SavingsProduct.InterestCalculationDaysInYearType,
    val withdrawalFeeForTransfers: Boolean,
    val allowOverdraft: Boolean,
    val enforceMinRequiredBalance: Boolean,
    val withHoldTax: Boolean,
    val accountingRule: SavingsProduct.AccountingRule,
    val currencyOptions: List<Currency>,
    val interestCompoundingPeriodTypeOptions: List<InterestCompoundingPeriodTypeOption>,
    val interestPostingPeriodTypeOptions: List<InterestPostingPeriodTypeOption>,
    val interestCalculationTypeOptions: List<InterestCalculationTypeOption>,
    val interestCalculationDaysInYearTypeOptions: List<InterestCalculationDaysInYearTypeOption>,
    val lockinPeriodFrequencyTypeOptions: List<LockinPeriodFrequencyTypeOption>,
    val withdrawalFeeTypeOptions: List<WithdrawalFeeTypeOption>,
    val paymentTypeOptions: List<PaymentTypeOption>,
    val accountingRuleOptions: List<AccountingRuleOption>,
    val accountingMappingOptions: AccountingMappingOptions,
    val chargeOptions: List<ChargeOption>,
    val taxGroupOptions: List<TaxGroupOption>,
    val isDormancyTrackingActive: Boolean
) {

    @JvmInline
    @Serializable
    value class AccountingRuleOption(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class WithdrawalFeeTypeOption(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class LockinPeriodFrequencyTypeOption(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class InterestCalculationDaysInYearTypeOption(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class InterestCalculationTypeOption(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class InterestPostingPeriodTypeOption(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class InterestCompoundingPeriodTypeOption(val data: EnumOptionData)


    @Serializable
    data class PaymentTypeOption(
        val id: Int,
        val name: String,
        val description: String,
        val isCashPayment: Boolean,
        val position: Int
    )



    @Serializable
    data class AccountingMappingOptions(
        val liabilityAccountOptions: List<LiabilityAccountOption>,
        val expenseAccountOptions: List<ExpenseAccountOption>,
        val assetAccountOptions: List<AssetAccountOption>,
        val incomeAccountOptions: List<IncomeAccountOption>,
        val equityAccountOptions: List<EquityAccountOption>
    ) {

        @JvmInline
        @Serializable
        value class Type(val data: EnumOptionData)

        @JvmInline
        @Serializable
        value class Usage(val data: EnumOptionData)

        @Serializable
        data class TagId(
            val id: Int,
            val active: Boolean,
            val mandatory: Boolean
        )

        @Serializable
        data class LiabilityAccountOption(
            val id: Int,
            val name: String,
            val glCode: String,
            val disabled: Boolean,
            val manualEntriesAllowed: Boolean,
            val type: Type,
            val usage: Usage,
            val nameDecorated: String,
            val tagId: TagId,
            val parentId: Int?
        )

        @Serializable
        data class ExpenseAccountOption(
            val id: Int,
            val name: String,
            val glCode: String,
            val disabled: Boolean,
            val manualEntriesAllowed: Boolean,
            val type: Type,
            val usage: Usage,
            val nameDecorated: String,
            val tagId: TagId
        )

        @Serializable
        data class AssetAccountOption(
            val id: Int,
            val name: String,
            val parentId: Int?,
            val glCode: String,
            val disabled: Boolean,
            val manualEntriesAllowed: Boolean,
            val type: Type,
            val usage: Usage,
            val description: String?,
            val nameDecorated: String,
            val tagId: TagId
        )


        @Serializable
        data class IncomeAccountOption(
            val id: Int,
            val name: String,
            val glCode: String,
            val disabled: Boolean,
            val manualEntriesAllowed: Boolean,
            val type: Type,
            val usage: Usage,
            val nameDecorated: String,
            val tagId: TagId
        )

        @Serializable
        data class EquityAccountOption(
            val id: Int,
            val name: String,
            val glCode: String,
            val disabled: Boolean,
            val manualEntriesAllowed: Boolean,
            val type: Type,
            val usage: Usage,
            val nameDecorated: String,
            val tagId: TagId
        )
    }

    @Serializable
    data class ChargeOption(
        val id: Int,
        val name: String,
        val active: Boolean,
        val penalty: Boolean,
        val currency: Currency,
        val amount: Double,
        val chargeTimeType: ChargeTimeType,
        val chargeAppliesTo: ChargeAppliesTo,
        val chargeCalculationType: ChargeCalculationType,
        val chargePaymentMode: ChargePaymentMode
    ) {

        @JvmInline
        @Serializable
        value class ChargeTimeType(val data: EnumOptionData)


        @JvmInline
        @Serializable
        value class ChargeCalculationType(val data: EnumOptionData)


        @JvmInline
        @Serializable
        value class ChargeAppliesTo(val data: EnumOptionData)

        @JvmInline
        @Serializable
        value class ChargePaymentMode(val data: EnumOptionData)

    }

    @Serializable
    data class TaxGroupOption(
        val id: Int,
        val name: String
    )
}