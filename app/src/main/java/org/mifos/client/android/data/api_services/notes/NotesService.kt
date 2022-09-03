package org.mifos.client.android.data.api_services.notes

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NotesService {
    @GET("{entityType}/{entityId}/notes")
    suspend fun getNotes(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int
    ): ApiResponse<List<Note>>
}
