package org.mifos.client.android.data.api_services.survey

import kotlinx.serialization.Serializable

@Serializable
data class SurveyScorecard(
    val userId: Int,
    val clientId: Int = 0,
    val createdOn: String,
    val scorecardValues: List<ScorecardValues>
){
    @Serializable
    data class ScorecardValues(
        val questionId: Int,
        val responseId: Int,
        val value: Int
    )
}