package org.mifos.client.core.checkerinbox

import kotlinx.serialization.Serializable


@Serializable
data class RescheduleLoansTask(
    val id: Int,
    val clientName: String,
    val loanAccountNumber: String,
    val rescheduleFromDate: List<Int>,
    val actionName: String,
    val rescheduleReasonCodeValue: RescheduleReasonCodeValue
){

    @Serializable
    data class RescheduleReasonCodeValue (
        val id : Int, 
        val name : String,
        val active : Boolean, 
        val mandatory : Boolean
    )
}