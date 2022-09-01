package org.mifos.client.core.savingsaccounts


import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class PostTransactionRequest(
    val savingAccountId: Int,
    val savingsAccountType: String,
    val transactionType: String,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    val transactionDate: String,
    val transactionAmount: String,
    val paymentTypeId: String,
    val accountNumber: String,
    val checkNumber: String,
    val routingCode: String,
    val receiptNumber: String,
    val bankNumber: String,
    val note: String? = null,
    val errorMessage: String? = null
)