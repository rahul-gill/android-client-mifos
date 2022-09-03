package org.mifos.client.android.data.api_services.common

import kotlinx.serialization.Serializable

@Serializable
data class Page<T>(
    val totalFilteredRecords: Int,
    val pageItems: List<T>
)