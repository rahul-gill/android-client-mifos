package org.mifos.client.android.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.mifos.client.android.data.api_services.auth.AuthPostRequest
import org.mifos.client.android.data.api_services.auth.AuthPostResponse
import org.mifos.client.android.data.api_services.auth.AuthService
import org.mifos.client.android.data.local_prefs.PrefsManager
import javax.inject.Inject

data class LoginScreenState(
    val userName: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false
)

sealed class LoginScreenEffects{
    object LoginFailed: LoginScreenEffects()
    object LoginSuccess: LoginScreenEffects()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    private val prefsManager: PrefsManager
): ViewModel() {

    val userName = mutableStateOf("")
    val password = mutableStateOf("")
    val isPasswordVisible = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    private val effects =  Channel<LoginScreenEffects>(Channel.UNLIMITED)
    val effectsFlow
        get() = effects.receiveAsFlow()

    fun login(){
        viewModelScope.launch {
            isLoading.value = true
            authService.authenticate(AuthPostRequest(userName.value, password.value))
                .suspendOnSuccess {
                    effects.send(LoginScreenEffects.LoginSuccess)
                    isLoading.value = false
                    onSuccessfulLogin(data)
                }
                .suspendOnFailure {
                    effects.send(LoginScreenEffects.LoginFailed)
                    isLoading.value = false
                }
        }
    }

    private fun onSuccessfulLogin(loginResponse: AuthPostResponse) {
        prefsManager.apply {
            token = loginResponse.base64EncodedAuthenticationKey
            username = loginResponse.username
            userId = loginResponse.userId
        }
    }
}