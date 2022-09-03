package org.mifos.client.android.data.api_services.survey


import kotlinx.serialization.Serializable

@Serializable
data class Survey(
    val id: Int,
    val componentDatas: List<ComponentData>,
    val questionDatas: List<QuestionData>,
    val key: String,
    val name: String,
    val description: String?,
    val countryCode: String,
    val validFrom: Long,
    val validTo: Long
) {
    @Serializable
    data class QuestionData(
        val id: Int,
        val responseDatas: List<ResponseData>,
        val componentKey: String?,
        val key: String,
        val text: String,
        val description: String?,
        val sequenceNo: Int
    ) {
        @Serializable
        data class ResponseData(
            val id: Int,
            val text: String,
            val value: Int,
            val sequenceNo: Int
        )
    }

    @Serializable
    data class ComponentData(
        val id: Int,
        val key: String,
        val text: String,
        val description: String?,
        val sequenceNo: Int
    )
}