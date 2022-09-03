package org.mifos.client.android.data.api_services.loans

import org.mifos.client.android.data.Consts
import kotlinx.serialization.Serializable


@Serializable
data class PostLoanDisbursementRequest(
    val actualDisbursementDate: String,
    val note: String,
    val transactionAmount: Double,
    val paymentTypeId: Int,
    val locale: String? = Consts.API_LOCALE,
    val dateFormat: String = Consts.API_DATE_FORMAT
)