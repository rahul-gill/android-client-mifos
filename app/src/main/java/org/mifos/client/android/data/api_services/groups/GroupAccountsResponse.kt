package org.mifos.client.android.data.api_services.groups

import org.mifos.client.android.data.api_services.loans.LoanAccount
import org.mifos.client.android.data.api_services.savingsaccounts.SavingsAccount
import kotlinx.serialization.Serializable

@Serializable
data class GroupAccountsResponse(
    val savingsAccounts: List<SavingsAccount>,
    val loanAccounts: List<LoanAccount>
)