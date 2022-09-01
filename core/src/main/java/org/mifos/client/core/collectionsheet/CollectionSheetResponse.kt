package org.mifos.client.core.collectionsheet

import org.mifos.client.core.loans.LoanProduct
import org.mifos.client.core.loans.LoanTransactionTemplate
import org.mifos.client.core.savingsaccounts.SavingsProduct
import kotlinx.serialization.Serializable

@Serializable
data class CollectionSheetResponse(
    val attendanceTypeOptions: List<IndividualCollectionSheet.ClientCollectionSheet.AttendanceTypeOption>,
    val dueDate: List<Int>,
    val groups: List<GroupCollectionSheet>,
    val loanProducts: List<LoanProduct>,
    val paymentTypeOptions: List<LoanTransactionTemplate.PaymentTypeOption>,
    val savingsProducts: List<SavingsProduct>,
) {
    @Serializable
    data class GroupCollectionSheet(
        val clients: List<ClientCollectionSheet>,
        val groupId: Int,
        val groupName: String,
        val levelId: Int,
        val levelName: String,
        val staffId: Int,
        val staffName: String
    ) {
        @Serializable
        data class ClientCollectionSheet(
            val clientId: Int,
            val clientName: String
        )
    }
}