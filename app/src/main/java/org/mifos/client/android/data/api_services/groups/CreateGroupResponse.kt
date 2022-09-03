package org.mifos.client.android.data.api_services.groups


import kotlinx.serialization.Serializable

@Serializable
data class CreateGroupResponse(
    val officeId: Int,
    val groupId: Int,
    val resourceId: Int
)