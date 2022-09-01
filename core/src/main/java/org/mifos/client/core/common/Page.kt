package org.mifos.client.core.common

import kotlinx.serialization.Serializable

@Serializable
data class Page<T>(
    val totalFilteredRecords: Int,
    val pageItems: List<T>
)