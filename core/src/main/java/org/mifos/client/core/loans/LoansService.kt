package org.mifos.client.core.loans

import org.mifos.client.core.ApiResponseFlow
import org.mifos.client.core.charges.Charge
import org.mifos.client.core.common.GenericResponse
import de.jensklingenberg.ktorfit.http.*


interface LoanService {
    @GET("loans/{loanId}associations=all&exclude=guarantors,futureSchedule")
    fun getLoanByIdWithAllAssociations(@Path("loanId") loanId: Int): ApiResponseFlow<LoanWithAssociations>

    @GET("loans/{loanId}/transactions/template?command=repayment")
    fun getLoanRepaymentTemplate(@Path("loanId") loanId: Int): ApiResponseFlow<LoanRepaymentTemplate>

    @POST("loans/{loanId}command=approve")
    fun approveLoanApplication(
        @Path("loanId") loanId: Int,
        @Body loanApproval: PostLoanApprovalRequest
    ): ApiResponseFlow<GenericResponse>

    @POST("loans/{loanId}/command=disburse")
    fun disburseLoan(
        @Path("loanId") loanId: Int,
        @Body loanDisbursement: PostLoanDisbursementRequest
    ): ApiResponseFlow<GenericResponse>

    @POST("loans/{loanId}/transactions?command=repayment")
    fun submitPayment(
        @Path("loanId") loanId: Int,
        @Body loanRepaymentRequest: PostLoanRepaymentRequest
    ): ApiResponseFlow<PostLoanRepaymentResponse>

    @GET("loans/{loanId}associations=repaymentSchedule")
    fun getLoanRepaymentSchedule(@Path("loanId") loanId: Int): ApiResponseFlow<LoanWithAssociations>

    @GET("loans/{loanId}associations=transactions")
    fun getLoanWithTransactions(@Path("loanId") loanId: Int): ApiResponseFlow<LoanWithAssociations>

    @GET("loanproducts")
    fun getAllLoans(): ApiResponseFlow<List<LoanProduct>>

    @POST("loans")
    fun createLoansAccount(@Body loansPayload: PostLoansRequest): ApiResponseFlow<PostLoansResponse>

    @GET("loans/template?templateType=individual")
    fun getLoansAccountTemplate(
        @Query("clientId") clientId: Int,
        @Query("productId") productId: Int
    ): ApiResponseFlow<LoanTemplate>

    @GET("loans/{loanId}/transactions/template")
    fun getLoanTransactionTemplate(
        @Path("loanId") loanId: Int,
        @Query("command") command: String = "disburse"
    ): ApiResponseFlow<LoanTransactionTemplate>

    @POST("loans")
    fun createGroupLoansAccount(@Body loansPayload: PostLoansRequest): ApiResponseFlow<PostLoansResponse>

    @GET("loans/template?templateType=group")
    fun getGroupLoansAccountTemplate(
        @Query("groupId") groupId: Int,
        @Query("productId") productId: Int
    ): ApiResponseFlow<GroupLoanTemplate>

    @GET("loans/{loanId}/charges")
    fun getListOfLoanCharges(@Path("loanId") loanId: Int): ApiResponseFlow<List<Charge>>

}

