package org.mifos.client.android.data.api_services.savingsaccounts


import kotlinx.serialization.Serializable

@Serializable
data class SavingsAccountTemplate(
    val withdrawalFeeForTransfers: Boolean,
    val allowOverdraft: Boolean,
    val enforceMinRequiredBalance: Boolean,
    val withHoldTax: Boolean,
    val isDormancyTrackingActive: Boolean,
    val productOptions: List<ProductOption>,
    val chargeOptions: List<ChargeOption>
) {
    @Serializable
    data class ProductOption(
        val id: Int,
        val name: String,
        val withdrawalFeeForTransfers: Boolean,
        val allowOverdraft: Boolean,
        val enforceMinRequiredBalance: Boolean,
        val withHoldTax: Boolean
    )

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
        data class ChargeTimeType(
            val id: Int,
            val code: String,
            val value: String
        )

        @Serializable
        data class ChargeAppliesTo(
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
        data class ChargePaymentMode(
            val id: Int,
            val code: String,
            val value: String
        )
    }
}