package org.mifos.client.android.data.api_services.common

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val code: String,
    val name: String,
    val decimalPlaces: Int,
    val inMultiplesOf: Int? = null,
    val displaySymbol: String,
    val nameCode: String,
    val displayLabel: String
)