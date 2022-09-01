package org.mifos.client.core.runreports


import kotlinx.serialization.Serializable

@Serializable
data class FullParameterListResponse(
    val columnHeaders: List<ColumnHeader>,
    val data: List<Data>
) {
    @Serializable
    data class ColumnHeader(
        val columnName: String,
        val columnType: String,
        val columnDisplayType: String,
        val isColumnNullable: Boolean,
        val isColumnPrimaryKey: Boolean,
        val columnValues: List<String>
    )

    @Serializable
    data class Data(
        val row: List<String>
    )
}