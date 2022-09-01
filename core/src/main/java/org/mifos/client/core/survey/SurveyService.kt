package org.mifos.client.core.survey

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.models.Scorecard
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path


interface SurveyService {
    @GET("surveys")
    fun getAllSurveys(): ApiResponseFlow<List<Survey>>

    @GET("surveys/{surveyId}")
    fun getSurvey(@Path("surveyId") surveyId: Int): ApiResponseFlow<Survey>

    @POST("surveys/{surveyId}/scorecards")
    fun submitScore(
        @Path("surveyId") surveyId: Int,
        @Body scorecardPayload: Scorecard
    ): ApiResponseFlow<Scorecard>
}
