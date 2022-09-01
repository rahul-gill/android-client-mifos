package org.mifos.client.core.collectionsheet

import org.mifos.client.core.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class CenterDetails(
    val staffId: Int,
    val staffName: String,
    val meetingFallCenters: List<MeetingFallCalendar>
) {
    @Serializable
    data class MeetingFallCalendar(
        val accountNo: String,
        val activationDate: List<Int>,
        val active: Boolean,
        val collectionMeetingCalendar: CollectionMeetingCalendar,
        val hierarchy: String,
        val id: Int,
        val installmentDue: Int,
        val name: String,
        val officeId: Int,
        val staffId: Int,
        val staffName: String,
        val status: Status,
        val totalCollected: Int,
        val totalOverdue: Int,
        val totaldue: Int,
    ) {

        @Serializable
        data class Status(
            var id: Int,
            var code: String,
            var value: String,
            var pendingApproval: Boolean,
            var waitingForDisbursal: Boolean,
            var active: Boolean,
            var closedObligationsMet: Boolean,
            var closedWrittenOff: Boolean,
            var closedRescheduled: Boolean,
            var closed: Boolean,
            var overpaid: Boolean,
        )

        @Serializable
        data class CollectionMeetingCalendar(
            val id: Int,
            val calendarInstanceId: Int,
            val entityId: Int,
            val entityType: EnumOptionData,
            val title: String,
            val startDate: List<Int> = ArrayList(),
            val duration: Int,
            val type: EnumOptionData,
            val repeating: Boolean,
            val recurrence: String,
            val frequency: EnumOptionData,
            val interval: Int,
            val repeatsOnNthDayOfMonth: EnumOptionData,
            val firstReminder: Int,
            val secondReminder: Int,
            val recurringDates: List<List<Int>> = ArrayList(),
            val nextTenRecurringDates: List<List<Int>> = ArrayList(),
            val humanReadable: String,
            val recentEligibleMeetingDate: List<Int> = ArrayList(),
            val createdDate: List<Int> = ArrayList(),
            val lastUpdatedDate: List<Int> = ArrayList(),
            val createdByUserId: Int,
            val createdByUsername: String,
            val lastUpdatedByUserId: Int,
            val lastUpdatedByUsername: String,
        )
    }
}
