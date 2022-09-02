package org.mifos.client.core.notes


import org.mifos.client.core.common.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val clientId: Int,
    val noteType: NoteType,
    val note: String,
    val createdById: Int,
    val createdByUsername: String,
    val createdOn: Long,
    val updatedById: Int,
    val updatedByUsername: String,
    val updatedOn: Long
) {
    @JvmInline
    @Serializable
    value class NoteType(val data: EnumOptionData)
}
