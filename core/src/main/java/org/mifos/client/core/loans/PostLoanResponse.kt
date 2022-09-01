package org.mifos.client.core.loans

import org.mifos.client.core.common.Currency
import kotlinx.serialization.Serializable

@Serializable
data class PostLoansResponse(
    val id: Int? = null,
    val accountNo: String? = null,
    val status: LoanAccount.Status? = null,
    val clientId: Int? = null,
    val clientName: String? = null,
    val clientOfficeId: Int? = null,
    val loanProductId: Int? = null,
    val loanProductName: String? = null,
    val loanProductDescription: String? = null,
    val fundId: Int? = null,
    val fundName: String? = null,
    val loanOfficerId: Int? = null,
    val loanOfficerName: String? = null,
    val loanType: LoanAccount.LoanType? = null,
    val currency: Currency? = null,
    val principal: Double? = null,
    val approvedPrincipal: Double? = null,
    val termFrequency: Int? = null,
    val numberOfRepayments: Int? = null,
    val repaymentEvery: Int? = null,
    val interestRatePerPeriod: Double? = null,
    val annualInterestRate: Double? = null,
    val transactionProcessingStrategyId: Int? = null,
    val transactionProcessingStrategyName: String? = null,
    val syncDisbursementWithMeeting: Boolean? = null,
    val feeChargesAtDisbursementCharged: Double? = null,
    val loanCounter: Int? = null,
    val loanProductCounter: Int? = null,
    val multiDisburseLoan: Boolean? = null,
    val canDisburse: Boolean? = null,
    val inArrears: Boolean? = null,
    val isNPA: Boolean? = null,
)