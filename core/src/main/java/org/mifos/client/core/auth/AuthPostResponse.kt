package org.mifos.client.core.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthPostResponse(
    val username: String,
    val userId: Int,
    val base64EncodedAuthenticationKey: String,
    val authenticated: Boolean,
    val officeId: Int,
    val officeName: String,
    val roles: List<Role>,
    val permissions: List<String>,
    val shouldRenewPassword: Boolean,
    val isTwoFactorAuthenticationRequired: Boolean
) {
    /**
     * Roles list from here: /fineract-provider/api/v1/roles
     */
    @Serializable
    data class Role(
        val id: Int,
        val name: String,
        val description: String,
        val disabled: Boolean
    )
}