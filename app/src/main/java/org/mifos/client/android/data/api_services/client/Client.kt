package org.mifos.client.android.data.api_services.client


import org.mifos.client.android.data.api_services.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val id: Int,
    val accountNo: String,
    val externalId: String? = null,
    val status: Status,
    val subStatus: SubStatus,
    val active: Boolean,
    val activationDate: List<Int>? = null,
    val firstname: String,
    val middlename: String? = null,
    val lastname: String,
    val displayName: String,
    val dateOfBirth: List<Int>? = null,
    val gender: Gender,
    val clientType: ClientType,
    val clientClassification: ClientClassification,
    val isStaff: Boolean,
    val officeId: Int,
    val officeName: String,
    val imageId: Int? = null,
    val imagePresent: Boolean? = null,
    val timeline: Timeline,
    val savingsAccountId: Int? = null,
    val legalForm: LegalForm? = null,
    val clientNonPersonDetails: ClientNonPersonDetails,
    val mobileNo: String? = null,
    val staffId: Int? = null,
    val staffName: String? = null,
    val savingsProductId: Int? = null,
    val savingsProductName: String? = null
) {
    @JvmInline
    @Serializable
    value class Status(val data: EnumOptionData)

    @Serializable
    data class SubStatus(
        val active: Boolean,
        val mandatory: Boolean
    )

    @Serializable
    data class Gender(
        val active: Boolean,
        val mandatory: Boolean,
        val id: Int? = null,
        val name: String? = null
    )

    @Serializable
    data class ClientType(
        val id: Int? = null,
        val name: String? = null,
        val active: Boolean,
        val mandatory: Boolean
    )

    @Serializable
    data class ClientClassification(
        val active: Boolean,
        val mandatory: Boolean,
        val id: Int? = null,
        val name: String? = null
    )

    @Serializable
    data class Timeline(
        val submittedOnDate: List<Int>,
        val submittedByUsername: String,
        val submittedByFirstname: String,
        val submittedByLastname: String,
        val activatedOnDate: List<Int>? = null,
        val activatedByUsername: String? = null,
        val activatedByFirstname: String? = null,
        val activatedByLastname: String? = null
    )

    @JvmInline
    @Serializable
    value class LegalForm(val data: EnumOptionData)


    @Serializable
    data class ClientNonPersonDetails(
        val constitution: Constitution,
        val mainBusinessLine: MainBusinessLine
    ) {
        @Serializable
        data class Constitution(
            val active: Boolean,
            val mandatory: Boolean
        )

        @Serializable
        data class MainBusinessLine(
            val active: Boolean,
            val mandatory: Boolean
        )
    }
}
