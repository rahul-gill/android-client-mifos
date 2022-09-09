package org.mifos.client.android.data.api_services.collectionsheet

import org.mifos.client.android.data.api_services.centers.Center
import org.mifos.client.android.data.api_services.groups.Group
import kotlinx.serialization.Serializable

@Serializable
data class CenterWithAssociations(
    private val id: Int,
    private val accountNo: String,
    private val name: String,
    private val externalId: String,
    private val officeId: Int,
    private val officeName: String,
    private val staffId: Int,
    private val staffName: String,
    private val hierarchy: String,
    private val status: Center.Status,
    private val active: Boolean,
    private val activationDate: List<Int>,
    private val timeline: Center.Timeline,
    private val groupMembers: List<Group> = listOf(),
    private val collectionMeetingCalendar: CenterDetails.MeetingFallCalendar.CollectionMeetingCalendar
)