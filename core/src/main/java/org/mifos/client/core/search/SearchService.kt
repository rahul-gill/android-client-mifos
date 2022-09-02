package org.mifos.client.core.search

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchService {
    /**
     * Resource Values: clients,groups,loans,savingsaccounts
     */
    @GET("search")
    suspend fun searchResources(
        @Query("query") query: String,
        @Query("resource") resources: String = "clients,groups,loans,savingsaccounts",
        @Query("exactMatch") exactMatch: Boolean = false
    ): ApiResponse<List<SearchedEntity>>
}