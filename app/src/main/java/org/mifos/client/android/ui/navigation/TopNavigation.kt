package org.mifos.client.android.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import dev.olshevski.navigation.reimagined.*
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import org.mifos.client.android.app.auth.LoginScreen
import org.mifos.client.android.app.auth.LoginViewModel
import org.mifos.client.android.ui.lock_screen.PassCodeCreateScreen
import org.mifos.client.android.ui.lock_screen.PassCodeScreen
import org.mifos.client.android.ui.lock_screen.PassCodeViewModel
import java.time.Instant
import java.time.temporal.ChronoUnit

enum class UserState {
    LoggedOut, LoggedInPasscodeUnset, LoggedInPasscodeSet, Authorized
}

enum class TopNavigationScreen{
    LoginScreen, PasscodeScreen, PasscodeCreateScreen, HomeScreen
}

@Composable
fun TopNavigationNavHost(
    userState: UserState,
    authenticateUser: (passcode: String) -> Boolean,
    setPasscode: (passcode: String) -> Unit
) {
    val navController = rememberNavController(
        startDestination = when (userState) {
            UserState.LoggedOut -> TopNavigationScreen.LoginScreen
            UserState.LoggedInPasscodeUnset -> TopNavigationScreen.PasscodeCreateScreen
            UserState.LoggedInPasscodeSet -> TopNavigationScreen.PasscodeScreen
            UserState.Authorized -> TopNavigationScreen.HomeScreen
        }
    )
    val lifecycleOwner = LocalLifecycleOwner.current
    var timeWhenAppWentInBackground  by remember { mutableStateOf(Instant.now()) }


    /**
     * When the app in background for long enough and then opened again,
     * The passcode screen should show
     */
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP){
                timeWhenAppWentInBackground = Instant.now()
            }
            else if (event == Lifecycle.Event.ON_START
                && timeWhenAppWentInBackground.plus(5, ChronoUnit.MINUTES).isBefore(Instant.now())
                && navController.backstack.entries.last().destination != TopNavigationScreen.PasscodeScreen
            )
                navController.navigate(TopNavigationScreen.PasscodeScreen)
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    NavBackHandler(navController)

    NavHost(navController) { screen ->
        when (screen) {
            TopNavigationScreen.LoginScreen -> {
                val viewModel: LoginViewModel = hiltViewModel()

                LoginScreen(
                    username = viewModel.userName.value,
                    onUsernameChange = { viewModel.userName.value = it },
                    password = viewModel.password.value,
                    onPasswordChange = { viewModel.password.value = it },
                    isPasswordVisible = viewModel.isPasswordVisible.value,
                    onSwitchPasswordVisibility = {
                        viewModel.isPasswordVisible.value = !viewModel.isPasswordVisible.value
                    },
                    isLoading = viewModel.isLoading.value,
                    onLogin = { viewModel.login() },
                    onLoginSuccess = {
                        navController.setNewBackstack(listOf(navEntry(TopNavigationScreen.PasscodeCreateScreen)))
                    },
                    effectsFlow = viewModel.effectsFlow
                )
            }
            TopNavigationScreen.HomeScreen -> {
                HomeNavigationNavHost()
            }
            TopNavigationScreen.PasscodeCreateScreen -> {
                PassCodeCreateScreen(
                    setPasscode = {
                        setPasscode(it)
                        navController.setNewBackstack(listOf(navEntry(TopNavigationScreen.HomeScreen)))
                    },
                )
            }
            TopNavigationScreen.PasscodeScreen -> {
                val viewModel : PassCodeViewModel = hiltViewModel()
                PassCodeScreen(
                    enterPassCode = {
                        val result = authenticateUser(it)
                        if (result) {
                            if (navController.backstack.entries.size == 1)
                                navController.setNewBackstack(listOf(navEntry(TopNavigationScreen.HomeScreen)))
                            else
                                navController.pop()
                        }
                        result
                    },
                    onManualLogin = {
                        viewModel.logout()
                        navController.navigate(TopNavigationScreen.LoginScreen)
                        navController.setNewBackstack(listOf(navEntry(TopNavigationScreen.LoginScreen)))
                    }
                )
            }
        }
    }

}