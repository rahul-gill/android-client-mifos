package org.mifos.client.core.centers


import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class GetCollectionSheetPayload(
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    val calendarId: Int,
    val transactionDate: String
)