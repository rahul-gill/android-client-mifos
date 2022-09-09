package org.mifos.client.android.data.api_services.collectionsheet

import org.mifos.client.android.data.ApiDefaults

data class GenerateCollectionSheetPayload(
    val dateFormat: String = ApiDefaults.API_DATE_FORMAT,
    val locale: String = ApiDefaults.API_LOCALE,
    val officeId: Int,
    val staffId: Int,
    val transactionDate: String
)