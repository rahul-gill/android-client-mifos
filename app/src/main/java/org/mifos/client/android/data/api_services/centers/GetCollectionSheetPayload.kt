package org.mifos.client.android.data.api_services.centers


import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class GetCollectionSheetPayload(
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val locale: String = ApiDefaults.API_LOCALE,
    val calendarId: Int,
    val transactionDate: String
)