package org.mifos.client.android.data.api_services.checkerinbox

import kotlinx.serialization.Serializable

@Serializable
data class CheckerInboxSearchTemplate(
    val actionNames: List<String>,
    val entityNames: List<String>
)