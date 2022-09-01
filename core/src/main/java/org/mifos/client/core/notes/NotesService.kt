package org.mifos.client.core.notes

import org.mifos.client.core.ApiResponseFlow
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path

interface NotesService {
    @GET("{entityType}/{entityId}/notes")
    fun getNotes(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int
    ): ApiResponseFlow<List<Note>>
}
