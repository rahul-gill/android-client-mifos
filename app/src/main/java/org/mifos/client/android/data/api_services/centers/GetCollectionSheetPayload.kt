package org.mifos.client.android.data.api_services.centers


import org.mifos.client.android.data.Consts
import kotlinx.serialization.Serializable

@Serializable
data class GetCollectionSheetPayload(
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    val calendarId: Int,
    val transactionDate: String
)