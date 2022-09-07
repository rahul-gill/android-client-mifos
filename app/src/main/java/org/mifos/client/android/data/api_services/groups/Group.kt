package org.mifos.client.android.data.api_services.groups

import org.mifos.client.android.data.api_services.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: Int,
    val accountNo: String,
    val name: String,
    val status: Status,
    val active: Boolean,
    val activationDate: List<Int>? = null,
    val officeId: Int,
    val officeName: String,
    val staffId: Int? = null,
    val staffName: String? = null,
    val hierarchy: String,
    val groupLevel: String,
    val timeline: Timeline,
    val externalId: String? = null,
) {

    @JvmInline
    @Serializable
    value class Status(val data: EnumOptionData)

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
}