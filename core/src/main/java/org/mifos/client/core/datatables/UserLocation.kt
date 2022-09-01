package org.mifos.client.core.datatables

import kotlinx.serialization.Serializable

@Serializable
data class UserLocation(
    val user_id: Int,
    val latlng: String,
    val start_time: String,
    val stop_time: String,
    val date: String
)