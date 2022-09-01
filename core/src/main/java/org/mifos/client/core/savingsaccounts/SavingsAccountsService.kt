package org.mifos.client.core.savingsaccounts

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.common.ActivationPayload
import org.mifos.client.core.common.ApprovalPayload
import org.mifos.client.core.common.GenericResponse
import de.jensklingenberg.ktorfit.http.*

interface SavingsAccountsService {

    @GET("/{savingsAccountType}/{savingsAccountId}?associations=transactions")
    fun getSavingsAccountWithAssociations(
        @Path("savingsAccountType") savingsAccountType: String = "savingsaccounts",
        @Path("savingsAccountId") savingsAccountId: Int,
    ): ApiResponseFlow<SavingsAccountWithTransactions>

    @GET("/{savingsAccountType}/{savingsAccountId}/transactions/template")
    fun getSavingsAccountTransactionTemplate(
        @Path("savingsAccountType") savingsAccountType: String = "savingsaccounts",
        @Path("savingsAccountId") savingsAccountId: Int,
        @Query("command") transactionType: String
    ): ApiResponseFlow<SavingsAccountTransactionTemplate>

    /**
     * Action: Deposit, Withdrawal
     */
    @POST("{savingsAccountType}/{savingsAccountId}/transactions")
    fun processTransaction(
        @Path("savingsAccountType") savingsAccountType: String = "savingsaccounts",
        @Path("savingsAccountId") savingsAccountId: Int,
        @Query("command") transactionType: String,
        @Body savingsAccountTransactionRequest: PostTransactionRequest
    ): ApiResponseFlow<PostTransactionResponse>

    @POST("savingsaccounts/{savingsAccountId}/command=activate")
    fun activateSavings(
        @Path("savingsAccountId") savingsAccountId: Int,
        @Body body: ActivationPayload
    ): ApiResponseFlow<GenericResponse>

    @POST("savingsaccounts/{savingsAccountId}command=approve")
    fun approveSavingsApplication(
        @Path("savingsAccountId") savingsAccountId: Int,
        @Body savingsApproval: ApprovalPayload
    ): ApiResponseFlow<GenericResponse>

    @GET("savingsproducts")
    fun getAllSavingsProducts(): ApiResponseFlow<List<SavingsProduct>>

    @POST("savingsaccounts")
    fun createSavingsAccount(@Body savingsPayload: CreateSavingsAccountRequest): ApiResponseFlow<CreateSavingsAccountResponse>

    @GET("savingsproducts/template")
    fun getSavingsProductsTemplate(): ApiResponseFlow<SavingsProductTemplate>

    @GET("savingsaccounts/template")
    fun getClientSavingsAccountTemplateByProduct(
        @Query("clientId") clientId: Int,
        @Query("productId") productId: Int
    ): ApiResponseFlow<SavingsAccountTemplate>

    @GET("savingsaccounts/template")
    fun getGroupSavingsAccountTemplateByProduct(
        @Query("groupId") groupId: Int,
        @Query("productId") productId: Int
    ): ApiResponseFlow<SavingsAccountTemplate>
}
