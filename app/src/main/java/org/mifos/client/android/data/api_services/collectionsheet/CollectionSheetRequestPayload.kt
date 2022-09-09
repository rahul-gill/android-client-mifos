package org.mifos.client.android.data.api_services.collectionsheet

import org.mifos.client.android.data.ApiDefaults
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSheetRequestPayload (
    val calendarId: Int,
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val locale: String = ApiDefaults.API_LOCALE,
    val transactionDate: String
)
