package org.mifos.client.android.data.api_services.common

import kotlinx.serialization.Serializable

@Serializable
data class EnumOptionData(
    val id: Long,
    val code: String,
    val value: String
)