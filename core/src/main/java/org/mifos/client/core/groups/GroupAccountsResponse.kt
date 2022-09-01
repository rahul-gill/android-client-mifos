package org.mifos.client.core.groups

import org.mifos.client.core.loans.LoanAccount
import org.mifos.client.core.savingsaccounts.SavingsAccount
import kotlinx.serialization.Serializable

@Serializable
data class GroupAccountsResponse(
    val savingsAccounts: List<SavingsAccount>,
    val loanAccounts: List<LoanAccount>
)