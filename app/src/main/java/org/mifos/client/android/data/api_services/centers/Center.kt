package org.mifos.client.android.data.api_services.centers

import org.mifos.client.android.data.api_services.common.EnumOptionData
import org.mifos.client.android.data.api_services.groups.Group
import kotlinx.serialization.Serializable


@Serializable
data class Center(
    val id: Int,
    val accountNo: String,
    val name: String,
    val externalId: String? = null,
    val officeId: Int,
    val officeName: String,
    val hierarchy: String,
    val status: Status,
    val active: Boolean,
    val activationDate: List<Int>? = null,
    val timeline: Timeline,
    val staffId: Int? = null,
    val staffName: String? = null,
    val groupMembers: List<Group>? = null,
    val collectionMeetingCalendar: CollectionMeetingCalendar? = null
) {

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
    value class Status(val data: EnumOptionData)
}

