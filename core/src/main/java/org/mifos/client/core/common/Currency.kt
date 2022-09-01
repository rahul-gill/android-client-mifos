package org.mifos.client.core.common

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