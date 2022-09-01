package org.mifos.client.core.client

import org.mifos.client.core.loans.LoanAccount
import org.mifos.client.core.savingsaccounts.SavingsAccount
import kotlinx.serialization.Serializable

@Serializable
data class ClientAccountsResponse(
    val savingsAccounts: List<SavingsAccount>,
    val loanAccounts: List<LoanAccount>
)