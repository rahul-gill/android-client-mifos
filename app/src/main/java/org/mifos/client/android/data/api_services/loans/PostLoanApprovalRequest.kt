package org.mifos.client.android.data.api_services.loans


import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class PostLoanApprovalRequest(
    val approvedLoanAmount: String,
    val approvedOnDate: String,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val expectedDisbursementDate: String,
    val locale: String = ApiDefaults.API_LOCALE,
    val note: String,
)