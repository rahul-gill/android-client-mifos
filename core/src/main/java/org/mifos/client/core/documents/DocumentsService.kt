package org.mifos.client.core.documents

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.core.common.GenericResponse
import retrofit2.http.*
import okhttp3.ResponseBody


interface DocumentService {
    @GET("{entityType}/{entityId}/documents")
    suspend fun getDocuments(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int
    ): ApiResponse<List<Document>>

    @POST("{entityType}/{entityId}/documents")
    @Multipart
    suspend fun createDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Part("name") nameOfDocument: String,
        @Part("description") description: String,
        @Part typedFile: Part
    ): ApiResponse<GenericResponse>

    @GET("{entityType}/{entityId}/documents/{documentId}/attachment")
    suspend fun downloadDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Path("documentId") documentId: Int
    ): ApiResponse<ResponseBody>

    @DELETE("{entityType}/{entityId}/documents/{documentId}")
    suspend fun removeDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Path("documentId") documentId: Int
    ): ApiResponse<GenericResponse>

    @PUT("{entityType}/{entityId}/documents/{documentId}")
    @Multipart
    suspend fun updateDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Path("documentId") documentId: Int,
        @Part("name") nameOfDocument: String,
        @Part("description") description: String,
        @Part typedFile: Part
    ): ApiResponse<GenericResponse>
}
