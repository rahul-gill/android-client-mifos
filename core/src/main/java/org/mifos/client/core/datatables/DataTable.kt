package org.mifos.client.core.datatables


import kotlinx.serialization.Serializable

@Serializable
data class DataTable(
    val applicationTableName: String,
    val registeredTableName: String,
    val columnHeaderData: List<ColumnHeaderData>
) {
    @Serializable
    data class ColumnHeaderData(
        val columnName: String,
        val columnType: String,
        val columnLength: Int,
        val columnDisplayType: String,
        val isColumnNullable: Boolean,
        val isColumnPrimaryKey: Boolean,
        val columnValues: List<ColumnValue>,
        val columnCode: String?
    ) {
        @Serializable
        data class ColumnValue(
            val id: Int,
            val value: String,
            val score: Int
        )
    }
}
