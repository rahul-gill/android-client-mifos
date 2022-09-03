package org.mifos.client.android.data.local_prefs

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PrefsManager @Inject constructor(@ApplicationContext private val context: Context) {

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
        get() = prefs.getInt(USER_ID_KEY, -1)
        set(value) = prefs.edit { putInt(USER_ID_KEY, value) }

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
        private const val USER_ID_KEY = "USER_ID_KEY"
        private const val TOKEN_KEY = "token"
        private const val PASS_CODE_KEY = "PASS_CODE_KEY"
        private const val BASE_URL_KEY = "BASE_URL_KEY"
        private const val TENANT_KEY = "TENANT_KEY"
    }
}