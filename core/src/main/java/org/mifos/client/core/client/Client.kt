package org.mifos.client.core.client


import org.mifos.client.core.models.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val id: Int,
    val accountNo: String,
    val externalId: String?,
    val status: Status,
    val subStatus: SubStatus,
    val active: Boolean,
    val activationDate: List<Int>?,
    val firstname: String,
    val middlename: String?,
    val lastname: String,
    val displayName: String,
    val dateOfBirth: List<Int>?,
    val gender: Gender,
    val clientType: ClientType,
    val clientClassification: ClientClassification,
    val isStaff: Boolean,
    val officeId: Int,
    val officeName: String,
    val imageId: Int?,
    val imagePresent: Boolean?,
    val timeline: Timeline,
    val savingsAccountId: Int?,
    val legalForm: LegalForm?,
    val clientNonPersonDetails: ClientNonPersonDetails,
    val mobileNo: String?,
    val staffId: Int?,
    val staffName: String?,
    val savingsProductId: Int?,
    val savingsProductName: String?
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
        val id: Int?,
        val name: String?
    )

    @Serializable
    data class ClientType(
        val id: Int?,
        val name: String?,
        val active: Boolean,
        val mandatory: Boolean
    )

    @Serializable
    data class ClientClassification(
        val active: Boolean,
        val mandatory: Boolean,
        val id: Int?,
        val name: String?
    )

    @Serializable
    data class Timeline(
        val submittedOnDate: List<Int>,
        val submittedByUsername: String,
        val submittedByFirstname: String,
        val submittedByLastname: String,
        val activatedOnDate: List<Int>?,
        val activatedByUsername: String?,
        val activatedByFirstname: String?,
        val activatedByLastname: String?
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
