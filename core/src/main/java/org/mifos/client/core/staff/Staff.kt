package org.mifos.client.core.staff


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Staff(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val displayName: String,
    val officeId: Int,
    val officeName: String,
    val isLoanOfficer: Boolean,
    val isActive: Boolean,
    val joiningDate: List<Int>,
    val mobileNo: String?
)
