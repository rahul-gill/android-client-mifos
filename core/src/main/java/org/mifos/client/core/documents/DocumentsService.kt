package org.mifos.client.core.documents

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.common.GenericResponse
import de.jensklingenberg.ktorfit.http.*
import io.ktor.client.statement.*


interface DocumentService {
    @GET("{entityType}/{entityId}/documents")
    fun getDocuments(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int
    ): ApiResponseFlow<List<Document>>

    @POST("{entityType}/{entityId}/documents")
    @Multipart
    fun createDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Part("name") nameOfDocument: String,
        @Part("description") description: String,
        @Part typedFile: Part
    ): ApiResponseFlow<GenericResponse>

    @GET("{entityType}/{entityId}/documents/{documentId}/attachment")
    fun downloadDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Path("documentId") documentId: Int
    ): ApiResponseFlow<HttpResponse>

    @DELETE("{entityType}/{entityId}/documents/{documentId}")
    fun removeDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Path("documentId") documentId: Int
    ): ApiResponseFlow<GenericResponse>

    @PUT("{entityType}/{entityId}/documents/{documentId}")
    @Multipart
    fun updateDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Path("documentId") documentId: Int,
        @Part("name") nameOfDocument: String,
        @Part("description") description: String,
        @Part typedFile: Part
    ): ApiResponseFlow<GenericResponse>
}
