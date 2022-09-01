package org.mifos.client.core.collectionsheet

import org.mifos.client.core.Consts
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSheetRequestPayload (
    val calendarId: Int,
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    val transactionDate: String
)
