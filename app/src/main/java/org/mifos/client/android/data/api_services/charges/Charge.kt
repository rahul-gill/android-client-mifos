package org.mifos.client.android.data.api_services.charges


import kotlinx.serialization.Serializable
import org.mifos.client.android.data.api_services.common.Currency
import org.mifos.client.android.data.api_services.common.EnumOptionData

@Serializable
data class Charge(
    val id: Int,
    val clientId: Int? = null,
    val loanId: Int? = null,
    val chargeId: Int,
    val name: String,
    val chargeTimeType: ChargeTimeType,
    val dueDate: List<Int>,
    val chargeCalculationType: ChargeCalculationType,
    val currency: Currency,
    val amount: Double,
    val amountPaid: Double,
    val amountWaived: Int,
    val amountWrittenOff: Int,
    val amountOutstanding: Double,
    val penalty: Boolean,
    val isActive: Boolean,
    val isPaid: Boolean,
    val isWaived: Boolean
) {

    @JvmInline
    @Serializable
    value class ChargeTimeType(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class ChargeCalculationType(val data: EnumOptionData)

}