package org.mifos.client.core.runreports

import org.mifos.client.core.ApiResponseFlow
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import de.jensklingenberg.ktorfit.http.QueryMap


interface RunReportsService {

    @GET("runreports/reportCategoryList")
    fun getReportCategories(
        @Query("R_reportCategory") category: String,
        @Query("genericResultSet") genericResultSet: Boolean,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponseFlow<List<ReportItem>>


    @GET("runreports/FullParameterList")
    fun getReportFullParameterList(
        @Query("R_reportListing") reportName: String,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponseFlow<FullParameterListResponse>

    @GET("runreports/{path}")
    fun getReportParameterDetails(
        @Path("path") parameterName: String,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponseFlow<FullParameterListResponse>

    @GET("runreports/{path}")
    fun getReportOffice(
        @Path("path") parameterName: String,
        @Query("R_officeId") office: Int,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponseFlow<FullParameterListResponse>

    @GET("runreports/{path}")
    fun getReportProduct(
        @Path("path") parameterName: String,
        @Query("R_currencyId") currency: String,
        @Query("parameterType") parameterType: Boolean
    ): ApiResponseFlow<FullParameterListResponse>

    @GET("runreports/{path}")
    fun getRunReportWithQuery(
        @Path("path") reportName: String,
        @QueryMap options: Map<String, String>
    ): ApiResponseFlow<FullParameterListResponse>

}
