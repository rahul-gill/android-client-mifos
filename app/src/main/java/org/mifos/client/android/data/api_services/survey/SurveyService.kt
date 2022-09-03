package org.mifos.client.android.data.api_services.survey

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface SurveyService {
    @GET("surveys")
    suspend fun getAllSurveys(): ApiResponse<List<Survey>>

    @GET("surveys/{surveyId}")
    suspend fun getSurvey(@Path("surveyId") surveyId: Int): ApiResponse<Survey>

    @POST("surveys/{surveyId}/scorecards")
    suspend fun submitScore(
        @Path("surveyId") surveyId: Int,
        @Body scorecardPayload: SurveyScorecard
    ): ApiResponse<SurveyScorecard>
}
