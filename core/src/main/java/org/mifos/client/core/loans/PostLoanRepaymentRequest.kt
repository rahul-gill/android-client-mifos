package org.mifos.client.core.loans


import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class PostLoanRepaymentRequest(
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    val transactionDate: String,
    val transactionAmount: String,
    val paymentTypeId: String,
    val note: String,
    val accountNumber: String,
    val checkNumber: String,
    val routingCode: String,
    val receiptNumber: String,
    val bankNumber: String
)