package org.mifos.client.android.data.api_services.loans


import kotlinx.serialization.Serializable

@Serializable
data class LoanTemplate(
    val isLoanProductLinkedToFloatingRate: Boolean,
    val isFloatingInterestRate: Boolean,
    val productOptions: List<ProductOption>,
    val canDisburse: Boolean,
    val isTopup: Boolean,
    val isInterestRecalculationEnabled: Boolean,
    val isVariableInstallmentsAllowed: Boolean,
    val isEqualAmortization: Boolean,
    val isRatesEnabled: Boolean
) {
    @Serializable
    data class ProductOption(
        val id: Int,
        val name: String,
        val includeInBorrowerCycle: Boolean,
        val useBorrowerCycle: Boolean,
        val isLinkedToFloatingInterestRates: Boolean,
        val isFloatingInterestRateCalculationAllowed: Boolean,
        val allowVariableInstallments: Boolean,
        val isInterestRecalculationEnabled: Boolean,
        val canDefineInstallmentAmount: Boolean,
        val canUseForTopup: Boolean,
        val isRatesEnabled: Boolean,
        val multiDisburseLoan: Boolean,
        val holdGuaranteeFunds: Boolean,
        val accountMovesOutOfNPAOnlyOnArrearsCompletion: Boolean,
        val syncExpectedWithDisbursementDate: Boolean,
        val isEqualAmortization: Boolean
    )
}