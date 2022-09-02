package org.mifos.client.core.local_prefs

import android.app.Application
import android.content.Context
import androidx.core.content.edit


class PrefsManager(private val context: Application) {

    private val prefs
        get() = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

    var username: String
        get() = prefs.getString(USERNAME_KEY, "") ?: ""
        set(value) = prefs.edit { putString(USERNAME_KEY, value) }

    var token: String
        get() = prefs.getString(TOKEN_KEY, "") ?: ""
        set(value) = prefs.edit { putString(TOKEN_KEY, value) }

    val isUserAuthenticated
        get() = token.isNotBlank()

    var userId
        get() = prefs.getInt(TOKEN_KEY, -1)
        set(value) = prefs.edit { putInt(TOKEN_KEY, value) }

    var tenant: String
        get() = prefs.getString(TENANT_KEY, "") ?: ""
        set(value) = prefs.edit { putString(TENANT_KEY, value) }

    var instanceBaseUrl: String
        get() = prefs.getString(BASE_URL_KEY, "") ?: ""
        set(value) = prefs.edit { putString(BASE_URL_KEY, value) }

    var passcode: String
        get() = prefs.getString(PASS_CODE_KEY, "") ?: ""
        set(value) = prefs.edit { putString(PASS_CODE_KEY, value) }


    val isPasscodeSet
        get() = passcode.isNotBlank()


    var isFirstTimeAppLaunch: Boolean
        get() = prefs.getBoolean(PASS_CODE_KEY, true)
        set(value) = prefs.edit { putBoolean(PASS_CODE_KEY, value) }




    companion object {
        private const val PREFERENCE_KEY = "local_prefs"
        private const val USERNAME_KEY = "username"
        private const val TOKEN_KEY = "token"
        private const val PASS_CODE_KEY = "PASS_CODE_KEY"
        private const val BASE_URL_KEY = "BASE_URL_KEY"
        private const val TENANT_KEY = "TENANT_KEY"
    }
}