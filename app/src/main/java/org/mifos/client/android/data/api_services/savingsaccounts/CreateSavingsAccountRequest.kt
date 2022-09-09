package org.mifos.client.android.data.api_services.savingsaccounts


import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class CreateSavingsAccountRequest(
    val clientId: Int,
    val productId: Int,
    val fieldOfficerId: Int,
    val locale: String = ApiDefaults.API_LOCALE,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val submittedOnDate: String,
    val accountNo: String? = null,
    val externalId: String? = null,
    val nominalAnnualInterestRate: String? = null,
    val interestCompoundingPeriodType: Int? = null,
    val interestPostingPeriodType: Int? = null,
    val interestCalculationType: Int? = null,
    val interestCalculationDaysInYearType: Int? = null,
    val minRequiredOpeningBalance: String? = null,
    val lockinPeriodFrequency: Int? = null,
    val lockinPeriodFrequencyType: Int? = null,
    val allowOverdraft: Boolean? = null,
    val overdraftLimit: Int? = null,
)