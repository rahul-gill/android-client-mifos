package org.mifos.client.android.data.api_services.client

import org.mifos.client.android.data.api_services.loans.LoanAccount
import org.mifos.client.android.data.api_services.savingsaccounts.SavingsAccount
import kotlinx.serialization.Serializable

@Serializable
data class ClientAccountsResponse(
    val savingsAccounts: List<SavingsAccount>,
    val loanAccounts: List<LoanAccount>
)