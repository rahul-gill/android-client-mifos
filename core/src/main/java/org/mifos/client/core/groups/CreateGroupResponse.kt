package org.mifos.client.core.groups


import kotlinx.serialization.Serializable

@Serializable
data class CreateGroupResponse(
    val officeId: Int,
    val groupId: Int,
    val resourceId: Int
)