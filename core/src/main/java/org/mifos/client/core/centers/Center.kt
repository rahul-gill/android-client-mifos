package org.mifos.client.core.centers

import org.mifos.client.core.common.EnumOptionData
import org.mifos.client.core.groups.Group
import kotlinx.serialization.Serializable


@Serializable
data class Center(
    val id: Int,
    val accountNo: String,
    val name: String,
    val externalId: String?,
    val officeId: Int,
    val officeName: String,
    val hierarchy: String,
    val status: Status,
    val active: Boolean,
    val activationDate: List<Int>?,
    val timeline: Timeline,
    val staffId: Int?,
    val staffName: String?,
    val groupMembers: List<Group>? = null,
    val collectionMeetingCalendar: CollectionMeetingCalendar? = null
) {

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
    value class Status(val data: EnumOptionData)
}

