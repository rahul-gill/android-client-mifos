package org.mifos.client.core.groups

import org.mifos.client.core.models.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: Int,
    val accountNo: String,
    val name: String,
    val status: Status,
    val active: Boolean,
    val activationDate: List<Int>,
    val officeId: Int,
    val officeName: String,
    val staffId: Int,
    val staffName: String,
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
        val activatedOnDate: List<Int>,
        val activatedByUsername: String,
        val activatedByFirstname: String,
        val activatedByLastname: String
    )
}