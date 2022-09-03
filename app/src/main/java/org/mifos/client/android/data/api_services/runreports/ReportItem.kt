package org.mifos.client.android.data.api_services.runreports


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportItem(
    @SerialName("report_id")
    val reportId: Int,
    @SerialName("report_name")
    val reportName: String,
    @SerialName("report_type")
    val reportType: String,
    @SerialName("report_subtype")
    val reportSubtype: String?,
    @SerialName("report_category")
    val reportCategory: String,
    @SerialName("parameter_id")
    val parameterId: Int,
    @SerialName("report_parameter_name")
    val reportParameterName: String?,
    @SerialName("parameter_name")
    val parameterName: String
)