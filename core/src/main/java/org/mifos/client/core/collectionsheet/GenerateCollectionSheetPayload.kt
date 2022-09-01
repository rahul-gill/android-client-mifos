package org.mifos.client.core.collectionsheet

import org.mifos.client.core.Consts

data class GenerateCollectionSheetPayload(
    val dateFormat: String = Consts.API_DATE_FORMAT,
    val locale: String = Consts.API_LOCALE,
    val officeId: Int,
    val staffId: Int,
    val transactionDate: String
)