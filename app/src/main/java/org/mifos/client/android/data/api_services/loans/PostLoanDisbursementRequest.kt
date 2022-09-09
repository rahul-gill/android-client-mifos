package org.mifos.client.android.data.api_services.loans

import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable


@Serializable
data class PostLoanDisbursementRequest(
    val actualDisbursementDate: String,
    val note: String,
    val transactionAmount: Double,
    val paymentTypeId: Int,
    val locale: String? = ApiDefaults.API_LOCALE,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT
)