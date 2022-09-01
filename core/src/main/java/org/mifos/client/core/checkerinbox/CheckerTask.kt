package org.mifos.client.core.checkerinbox


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckerTask(
    val id: Int,
    val actionName: String,
    val entityName: String,
    val resourceId: Int,
    val maker: String,
    val madeOnDate: Long,
    val processingResult: String,
    val officeName: String?,
    val clientName: String?
)