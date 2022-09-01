package org.mifos.client.core.search

import org.mifos.client.core.ApiResponseFlow
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query


interface SearchService {
    /**
     * Resource Values: clients,groups,loans,savingsaccounts
     */
    @GET("search")
    fun searchResources(
        @Query("query") query: String,
        @Query("resource") resources: String = "clients,groups,loans,savingsaccounts",
        @Query("exactMatch") exactMatch: Boolean = false
    ): ApiResponseFlow<List<SearchedEntity>>
}