package org.mifos.client.android.data.api_services.loans


import kotlinx.serialization.Serializable

@Serializable
data class GroupLoanTemplate(
    val group: Group,
    val isLoanProductLinkedToFloatingRate: Boolean,
    val isFloatingInterestRate: Boolean,
    val timeline: Timeline,
    val productOptions: List<ProductOption>,
    val loanOfficerOptions: List<LoanOfficerOption>,
    val canDisburse: Boolean,
    val isTopup: Boolean,
    val isInterestRecalculationEnabled: Boolean,
    val isVariableInstallmentsAllowed: Boolean,
    val isEqualAmortization: Boolean,
    val isRatesEnabled: Boolean
) {
    @Serializable
    data class Group(
        val id: Int,
        val accountNo: String,
        val name: String,
        val externalId: String,
        val status: Status,
        val active: Boolean,
        val activationDate: List<Int>,
        val officeId: Int,
        val officeName: String,
        val hierarchy: String,
        val groupLevel: String,
        val timeline: Timeline
    ) {
        @Serializable
        data class Status(
            val id: Int,
            val code: String,
            val value: String
        )

        @Serializable
        data class Timeline(
            val submittedOnDate: List<Int>,
            val submittedByUsername: String,
            val submittedByFirstname: String,
            val submittedByLastname: String,
            val activatedOnDate: List<Int>,
            val activatedByUsername: String,
            val activatedByFirstname: String,
            val activatedByLastname: String
        )
    }

    @Serializable
    data class Timeline(
        val expectedDisbursementDate: List<Int>
    )

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

    @Serializable
    data class LoanOfficerOption(
        val id: Int,
        val firstname: String,
        val lastname: String,
        val displayName: String,
        val mobileNo: String,
        val officeId: Int,
        val officeName: String,
        val isLoanOfficer: Boolean,
        val isActive: Boolean,
        val joiningDate: List<Int>
    )
}