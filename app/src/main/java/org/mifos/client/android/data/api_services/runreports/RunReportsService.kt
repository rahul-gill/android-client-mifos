package org.mifos.client.android.data.api_services.runreports

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface RunReportsService {

    @GET("runreports/reportCategoryList")
    suspend fun getReportCategories(
        @Query("R_reportCategory") category: String,
        @Query("genericResultSet") genericResultSet: Boolean,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponse<List<ReportItem>>


    @GET("runreports/FullParameterList")
    suspend fun getReportFullParameterList(
        @Query("R_reportListing") reportName: String,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponse<FullParameterListResponse>

    @GET("runreports/{path}")
    suspend fun getReportParameterDetails(
        @Path("path") parameterName: String,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponse<FullParameterListResponse>

    @GET("runreports/{path}")
    suspend fun getReportOffice(
        @Path("path") parameterName: String,
        @Query("R_officeId") office: Int,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponse<FullParameterListResponse>

    @GET("runreports/{path}")
    suspend fun getReportProduct(
        @Path("path") parameterName: String,
        @Query("R_currencyId") currency: String,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponse<FullParameterListResponse>

    @GET("runreports/{path}")
    suspend fun getRunReportWithQuery(
        @Path("path") reportName: String,
        @QueryMap options: Map<String, String>
    ): ApiResponse<FullParameterListResponse>

}
