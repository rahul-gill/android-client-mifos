package org.mifos.client.android.data.api_services.common


import kotlinx.serialization.Serializable

@Serializable
data class GenericResponse(
    val officeId: Int,
    val clientId: Int,
    val resourceId: Int
)