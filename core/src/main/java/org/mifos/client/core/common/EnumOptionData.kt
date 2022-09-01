package org.mifos.client.core.common

import kotlinx.serialization.Serializable

@Serializable
data class EnumOptionData(
    val id: Long,
    val code: String,
    val value: String
)