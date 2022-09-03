package org.mifos.client.android.data.api_services.collectionsheet

import org.mifos.client.android.data.Consts

data class GenerateCollectionSheetPayload(
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    val officeId: Int,
    val staffId: Int,
    val transactionDate: String
)