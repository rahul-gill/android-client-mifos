package org.mifos.client.core.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenericResponse(
    val officeId: Int,
    val clientId: Int,
    val resourceId: Int
)