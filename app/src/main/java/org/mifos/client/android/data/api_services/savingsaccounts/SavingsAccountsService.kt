package org.mifos.client.android.data.api_services.savingsaccounts

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.android.data.api_services.common.ActivationPayload
import org.mifos.client.android.data.api_services.common.ApprovalPayload
import org.mifos.client.android.data.api_services.common.GenericResponse
import retrofit2.http.*

interface SavingsAccountsService {

    @GET("{savingsAccountType}/{savingsAccountId}?associations=transactions")
    suspend fun getSavingsAccountWithAssociations(
        @Path("savingsAccountType") savingsAccountType: String = "savingsaccounts",
        @Path("savingsAccountId") savingsAccountId: Int,
    ): ApiResponse<SavingsAccountWithTransactions>

    @GET("{savingsAccountType}/{savingsAccountId}/transactions/template")
    suspend fun getSavingsAccountTransactionTemplate(
        @Path("savingsAccountType") savingsAccountType: String = "savingsaccounts",
        @Path("savingsAccountId") savingsAccountId: Int,
        @Query("command") transactionType: String
    ): ApiResponse<SavingsAccountTransactionTemplate>

    /**
     * Action: Deposit, Withdrawal
     */
    @POST("{savingsAccountType}/{savingsAccountId}/transactions")
    suspend fun processTransaction(
        @Path("savingsAccountType") savingsAccountType: String = "savingsaccounts",
        @Path("savingsAccountId") savingsAccountId: Int,
        @Query("command") transactionType: String,
        @Body savingsAccountTransactionRequest: PostTransactionRequest
    ): ApiResponse<PostTransactionResponse>

    @POST("savingsaccounts/{savingsAccountId}/command=activate")
    suspend fun activateSavings(
        @Path("savingsAccountId") savingsAccountId: Int,
        @Body body: ActivationPayload
    ): ApiResponse<GenericResponse>

    @POST("savingsaccounts/{savingsAccountId}command=approve")
    suspend fun approveSavingsApplication(
        @Path("savingsAccountId") savingsAccountId: Int,
        @Body savingsApproval: ApprovalPayload
    ): ApiResponse<GenericResponse>

    @GET("savingsproducts")
    suspend fun getAllSavingsProducts(): ApiResponse<List<SavingsProduct>>

    @POST("savingsaccounts")
    suspend fun createSavingsAccount(@Body savingsPayload: CreateSavingsAccountRequest): ApiResponse<CreateSavingsAccountResponse>

    @GET("savingsproducts/template")
    suspend fun getSavingsProductsTemplate(): ApiResponse<SavingsProductTemplate>

    @GET("savingsaccounts/template")
    suspend fun getClientSavingsAccountTemplateByProduct(
        @Query("clientId") clientId: Int,
        @Query("productId") productId: Int
    ): ApiResponse<SavingsAccountTemplate>

    @GET("savingsaccounts/template")
    suspend fun getGroupSavingsAccountTemplateByProduct(
        @Query("groupId") groupId: Int,
        @Query("productId") productId: Int
    ): ApiResponse<SavingsAccountTemplate>
}
