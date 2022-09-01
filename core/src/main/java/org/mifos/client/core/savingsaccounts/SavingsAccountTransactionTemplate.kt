package org.mifos.client.core.savingsaccounts


import org.mifos.client.core.common.Currency
import kotlinx.serialization.Serializable

@Serializable
data class SavingsAccountTransactionTemplate(
    val accountId: Int,
    val accountNo: String,
    val date: List<Int>,
    val currency: Currency,
    val reversed: Boolean,
    val interestedPostedAsOn: Boolean,
    val paymentTypeOptions: List<PaymentTypeOption>
) {

    @Serializable
    data class PaymentTypeOption(
        val id: Int,
        val name: String,
        val description: String,
        val isCashPayment: Boolean,
        val position: Int
    )
}