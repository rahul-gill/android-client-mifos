package org.mifos.client.android.data.api_services.savingsaccounts


import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class PostTransactionRequest(
    val savingAccountId: Int,
    val savingsAccountType: String,
    val transactionType: String,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val locale: String = ApiDefaults.API_LOCALE,
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