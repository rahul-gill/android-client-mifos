package org.mifos.client.android.data.api_services.centers

import org.mifos.client.android.data.api_services.loans.LoanAccount
import org.mifos.client.android.data.api_services.savingsaccounts.SavingsAccount
import kotlinx.serialization.Serializable

@Serializable
data class CenterAccountsResponse(
    val savingsAccounts: List<SavingsAccount>,
    val memberLoanAccounts: List<LoanAccount>,
    val loanAccounts: List<LoanAccount>
)