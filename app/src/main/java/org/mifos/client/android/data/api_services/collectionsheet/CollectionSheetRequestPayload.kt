package org.mifos.client.android.data.api_services.collectionsheet

import org.mifos.client.android.data.Consts
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSheetRequestPayload (
    val calendarId: Int,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    val transactionDate: String
)
