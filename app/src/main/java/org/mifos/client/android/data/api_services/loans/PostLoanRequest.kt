package org.mifos.client.android.data.api_services.loans

import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class PostLoansRequest (
    val allowPartialPeriodInterestCalcualtion: Boolean,
    val amortizationType: Int,
    val groupId: Int? = null,
    val clientId: Int? = null,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val expectedDisbursementDate: String,
    val interestCalculationPeriodType: Int,
    val interestRatePerPeriod: Double,
    val interestType: Int,
    val loanTermFrequency: Int,
    val loanTermFrequencyType: Int,
    val repaymentFrequencyDayOfWeekType: Int,
    val repaymentFrequencyNthDayType: Int,
    val loanType: String,
    val locale: String = ApiDefaults.API_LOCALE,
    val numberOfRepayments: String,
    val principal: String,
    val productId: Int,
    val repaymentEvery: String,
    val repaymentFrequencyType: Int,
    val submittedOnDate: String,
    val transactionProcessingStrategyId: Int,
    val loanPurposeId: Int,
    val linkAccountId: Int,
)