package org.mifos.client.core.loans

import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class PostLoansRequest (
    val allowPartialPeriodInterestCalcualtion: Boolean,
    val amortizationType: Int,
    val groupId: Int? = null,
    val clientId: Int? = null,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val expectedDisbursementDate: String,
    val interestCalculationPeriodType: Int,
    val interestRatePerPeriod: Double,
    val interestType: Int,
    val loanTermFrequency: Int,
    val loanTermFrequencyType: Int,
    val repaymentFrequencyDayOfWeekType: Int,
    val repaymentFrequencyNthDayType: Int,
    val loanType: String,
    val locale: String = Consts.API_LOCALE,
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