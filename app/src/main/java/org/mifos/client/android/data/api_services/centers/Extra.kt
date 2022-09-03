package org.mifos.client.android.data.api_services.centers

import org.mifos.client.android.data.api_services.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class CollectionMeetingCalendar(
    val id: Int,
    val calendarInstanceId: Int,
    val entityId: Int,
    val entityType: EntityType,
    val title: String,
    val startDate: List<Int>,
    val duration: Int,
    val type: Type,
    val repeating: Boolean,
    val recurrence: String,
    val frequency: Frequency,
    val interval: Int,
    val repeatsOnNthDayOfMonth: RepeatsOnNthDayOfMonth,
    val firstReminder: Int,
    val secondReminder: Int,
    val recurringDates: List<List<Int>>,
    val nextTenRecurringDates: List<List<Int>>,
    val humanReadable: String,
    val recentEligibleMeetingDate: List<Int>,
    val createdDate: List<Int>,
    val lastUpdatedDate: List<Int>,
    val createdByUserId: Int,
    val createdByUsername: String,
    val lastUpdatedByUserId: Int,
    val lastUpdatedByUsername: String
) {

    @JvmInline
    @Serializable
    value class EntityType(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class Type(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class Frequency(val data: EnumOptionData)

    @JvmInline
    @Serializable
    value class RepeatsOnNthDayOfMonth(val data: EnumOptionData)

}