package org.mifos.client.core.loans


import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class PostLoanApprovalRequest(
    val approvedLoanAmount: String,
    val approvedOnDate: String,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val expectedDisbursementDate: String,
    val locale: String = Consts.API_LOCALE,
    val note: String,
)