package org.mifos.client.core.centers

import org.mifos.client.core.loans.LoanAccount
import org.mifos.client.core.savingsaccounts.SavingsAccount
import kotlinx.serialization.Serializable

@Serializable
data class CenterAccountsResponse(
    val savingsAccounts: List<SavingsAccount>,
    val memberLoanAccounts: List<LoanAccount>,
    val loanAccounts: List<LoanAccount>
)