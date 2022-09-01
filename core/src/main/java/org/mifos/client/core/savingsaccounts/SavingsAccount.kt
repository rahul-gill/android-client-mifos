package org.mifos.client.core.savingsaccounts

import org.mifos.client.core.common.Currency
import org.mifos.client.core.models.EnumOptionData
import kotlinx.serialization.Serializable

@Serializable
data class SavingsAccount(
    val id: Int,
    val accountNo: String,
    val productId: Int,
    val productName: String,
    val shortProductName: String,
    val status: Status,
    val currency: Currency,
    val accountType: AccountType,
    val timeline: Timeline,
    val subStatus: SubStatus,
    val depositType: DepositType,
    val accountBalance: Double?,
    val lastActiveTransactionDate: List<Int>?
) {

    @JvmInline
    @Serializable
    value class AccountType(val data: EnumOptionData)

    @Serializable
    data class Status(
        val id: Int,
        val code: String,
        val value: String,
        val submittedAndPendingApproval: Boolean,
        val approved: Boolean,
        val rejected: Boolean,
        val withdrawnByApplicant: Boolean,
        val active: Boolean,
        val closed: Boolean,
        val prematureClosed: Boolean,
        val transferInProgress: Boolean,
        val transferOnHold: Boolean,
        val matured: Boolean
    )

    @Serializable
    data class Timeline(
        val submittedOnDate: List<Int>,
        val submittedByUsername: String,
        val submittedByFirstname: String,
        val submittedByLastname: String,
        val approvedOnDate: List<Int>?,
        val approvedByUsername: String?,
        val approvedByFirstname: String?,
        val approvedByLastname: String?,
        val activatedOnDate: List<Int>?,
        val activatedByUsername: String?,
        val activatedByFirstname: String?,
        val activatedByLastname: String?
    )

    @Serializable
    data class SubStatus(
        val id: Int,
        val code: String,
        val value: String,
        val none: Boolean,
        val inactive: Boolean,
        val dormant: Boolean,
        val escheat: Boolean,
        val block: Boolean,
        val blockCredit: Boolean,
        val blockDebit: Boolean
    )


    @JvmInline
    @Serializable
    value class DepositType(val data: EnumOptionData)
}
