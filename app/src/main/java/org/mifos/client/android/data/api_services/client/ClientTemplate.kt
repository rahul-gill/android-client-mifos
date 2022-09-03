package org.mifos.client.android.data.api_services.client


import kotlinx.serialization.Serializable

@Serializable
data class ClientTemplate(
    val officeOptions: List<OfficeOption>,
    val staffOptions: List<StaffOption>,
    val savingProductOptions: List<SavingProductOption>,
    val genderOptions: List<GenderOption>,
    val clientTypeOptions: List<ClientTypeOption>,
    val clientClassificationOptions: List<ClientClassificationOption>,
    val clientLegalFormOptions: List<ClientLegalFormOption>,
) {
    @Serializable
    data class OfficeOption(
        val id: Int,
        val name: String,
        val nameDecorated: String
    )

    @Serializable
    data class StaffOption(
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

    @Serializable
    data class SavingProductOption(
        val id: Int,
        val name: String,
        val withdrawalFeeForTransfers: Boolean,
        val allowOverdraft: Boolean,
        val enforceMinRequiredBalance: Boolean,
        val withHoldTax: Boolean
    )

    @Serializable
    data class GenderOption(
        val id: Int,
        val name: String,
        val position: Int,
        val description: String,
        val active: Boolean,
        val mandatory: Boolean
    )

    @Serializable
    data class ClientTypeOption(
        val id: Int,
        val name: String,
        val position: Int,
        val description: String,
        val active: Boolean,
        val mandatory: Boolean
    )

    @Serializable
    data class ClientClassificationOption(
        val id: Int,
        val name: String,
        val position: Int,
        val description: String,
        val active: Boolean,
        val mandatory: Boolean
    )

    @Serializable
    data class ClientLegalFormOption(
        val id: Int,
        val code: String,
        val value: String
    )
}