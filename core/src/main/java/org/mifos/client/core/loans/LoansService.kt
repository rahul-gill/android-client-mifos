package org.mifos.client.core.loans

import com.skydoves.sandwich.ApiResponse
import org.mifos.client.core.charges.Charge
import org.mifos.client.core.common.GenericResponse
import retrofit2.http.*


interface LoanService {
    @GET("loans/{loanId}associations=all&exclude=guarantors,futureSchedule")
    suspend fun getLoanByIdWithAllAssociations(@Path("loanId") loanId: Int): ApiResponse<LoanWithAssociations>

    @GET("loans/{loanId}/transactions/template?command=repayment")
    suspend fun getLoanRepaymentTemplate(@Path("loanId") loanId: Int): ApiResponse<LoanRepaymentTemplate>

    @POST("loans/{loanId}command=approve")
    suspend fun approveLoanApplication(
        @Path("loanId") loanId: Int,
        @Body loanApproval: PostLoanApprovalRequest
    ): ApiResponse<GenericResponse>

    @POST("loans/{loanId}/command=disburse")
    suspend fun disburseLoan(
        @Path("loanId") loanId: Int,
        @Body loanDisbursement: PostLoanDisbursementRequest
    ): ApiResponse<GenericResponse>

    @POST("loans/{loanId}/transactions?command=repayment")
    suspend fun submitPayment(
        @Path("loanId") loanId: Int,
        @Body loanRepaymentRequest: PostLoanRepaymentRequest
    ): ApiResponse<PostLoanRepaymentResponse>

    @GET("loans/{loanId}associations=repaymentSchedule")
    suspend fun getLoanRepaymentSchedule(@Path("loanId") loanId: Int): ApiResponse<LoanWithAssociations>

    @GET("loans/{loanId}associations=transactions")
    suspend fun getLoanWithTransactions(@Path("loanId") loanId: Int): ApiResponse<LoanWithAssociations>

    @GET("loanproducts")
    suspend fun getAllLoans(): ApiResponse<List<LoanProduct>>

    @POST("loans")
    suspend fun createLoansAccount(@Body loansPayload: PostLoansRequest): ApiResponse<PostLoansResponse>

    @GET("loans/template?templateType=individual")
    suspend fun getLoansAccountTemplate(
        @Query("clientId") clientId: Int,
        @Query("productId") productId: Int
    ): ApiResponse<LoanTemplate>

    @GET("loans/{loanId}/transactions/template")
    suspend fun getLoanTransactionTemplate(
        @Path("loanId") loanId: Int,
        @Query("command") command: String = "disburse"
    ): ApiResponse<LoanTransactionTemplate>

    @POST("loans")
    suspend fun createGroupLoansAccount(@Body loansPayload: PostLoansRequest): ApiResponse<PostLoansResponse>

    @GET("loans/template?templateType=group")
    suspend fun getGroupLoansAccountTemplate(
        @Query("groupId") groupId: Int,
        @Query("productId") productId: Int
    ): ApiResponse<GroupLoanTemplate>

    @GET("loans/{loanId}/charges")
    suspend fun getListOfLoanCharges(@Path("loanId") loanId: Int): ApiResponse<List<Charge>>

}

