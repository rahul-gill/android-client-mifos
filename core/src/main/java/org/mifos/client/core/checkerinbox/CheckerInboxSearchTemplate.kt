package org.mifos.client.core.checkerinbox

import kotlinx.serialization.Serializable

@Serializable
data class CheckerInboxSearchTemplate(
    val actionNames: List<String>,
    val entityNames: List<String>
)