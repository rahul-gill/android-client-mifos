package org.mifos.client.core.charges


import org.mifos.client.core.common.Currency
import org.mifos.client.core.models.EnumOptionData
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