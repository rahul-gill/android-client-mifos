package org.mifos.client.android.data.api_services.charges


import org.mifos.client.android.data.api_services.common.Currency
import org.mifos.client.android.data.api_services.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class ClientChargeTemplate(
    val penalty: Boolean,
    val chargeOptions: List<ChargeOption>
) {
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
        value class ChargeAppliesTo(val data: EnumOptionData)


        @JvmInline
        @Serializable
        value class ChargeCalculationType(val data: EnumOptionData)


        @JvmInline
        @Serializable
        value class ChargePaymentMode(val data: EnumOptionData)

    }
}