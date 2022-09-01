package org.mifos.client.core.loans

import org.mifos.client.core.Consts
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