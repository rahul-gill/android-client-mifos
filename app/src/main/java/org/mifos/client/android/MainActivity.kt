package org.mifos.client.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import org.mifos.client.android.data.local_prefs.PrefsManager
import org.mifos.client.android.ui.navigation.TopNavigationNavHost
import org.mifos.client.android.ui.navigation.UserState
import org.mifos.client.android.ui.theme.MifosAndroidClientTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var prefsManager: PrefsManager

    private val userAuthorizedWithPasscode = MutableStateFlow(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MifosAndroidClientTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userAuthed = userAuthorizedWithPasscode.collectAsState()
                    TopNavigationNavHost(
                        userState = when{
                            !prefsManager.isUserAuthenticated -> UserState.LoggedOut
                            prefsManager.isUserAuthenticated && !prefsManager.isPasscodeSet -> UserState.LoggedInPasscodeUnset
                            userAuthed.value -> UserState.Authorized
                            else -> UserState.LoggedInPasscodeSet
                        },
                        authenticateUser = {
                            userAuthorizedWithPasscode.value  = it == prefsManager.passcode
                            userAuthorizedWithPasscode.value
                        },
                        setPasscode = {
                            prefsManager.passcode = it
                        }
                    )
                }
            }
        }
    }
}