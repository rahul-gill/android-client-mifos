package org.mifos.client.android.ui.navigation

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import dev.olshevski.navigation.reimagined.*
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import kotlinx.parcelize.Parcelize
import org.mifos.client.android.auth.LoginScreen
import org.mifos.client.android.auth.LoginViewModel
import org.mifos.client.android.ui.lock_screen.PassCodeCreateScreen
import org.mifos.client.android.ui.lock_screen.PassCodeScreen

enum class UserState{
    LoggedOut, LoggedInPasscodeUnset, LoggedInPasscodeSet, Authorized
}

sealed class TopNavigationScreen{
    @Parcelize
    object LoginScreen : TopNavigationScreen(), Parcelable

    @Parcelize
    object PasscodeScreen : TopNavigationScreen(), Parcelable

    @Parcelize
    object  PasscodeCreateScreen : TopNavigationScreen(), Parcelable

    @Parcelize
    object  HomeScreen : TopNavigationScreen(), Parcelable
}

@Composable
fun TopNavigationNavHost(
    userState: UserState,
    authenticateUser: (passcode: String) -> Boolean,
    setPasscode: (passcode: String) -> Unit
){
    val navController = rememberNavController<TopNavigationScreen>(
        startDestination = when(userState){
            UserState.LoggedOut -> TopNavigationScreen.LoginScreen
            UserState.LoggedInPasscodeUnset -> TopNavigationScreen.PasscodeCreateScreen
            UserState.LoggedInPasscodeSet -> TopNavigationScreen.PasscodeScreen
            UserState.Authorized -> TopNavigationScreen.HomeScreen
        }
    )

    SideEffect {
        if(userState == UserState.LoggedInPasscodeSet
            && navController.backstack.entries.last().destination != TopNavigationScreen.PasscodeScreen)
            navController.navigate(TopNavigationScreen.PasscodeScreen)
    }

    NavBackHandler(navController)
    
    NavHost(navController) { screen ->
        when(screen){
            TopNavigationScreen.LoginScreen -> {
                val viewModel: LoginViewModel = hiltViewModel()

                LoginScreen(
                    username = viewModel.userName.value,
                    onUsernameChange = { viewModel.userName.value = it },
                    password = viewModel.password.value,
                    onPasswordChange = { viewModel.password.value = it },
                    isPasswordVisible = viewModel.isPasswordVisible.value,
                    onSwitchPasswordVisibility = { viewModel.isPasswordVisible.value = !viewModel.isPasswordVisible.value },
                    isLoading = viewModel.isLoading.value,
                    onLogin = { viewModel.login() },
                    onLoginSuccess = {
                        navController.setNewBackstack(listOf(navEntry(TopNavigationScreen.PasscodeCreateScreen)))
                    },
                    effectsFlow = viewModel.effectsFlow
                )
            }
            TopNavigationScreen.HomeScreen -> {

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
                PassCodeScreen(
                    enterPassCode = {
                        val result = authenticateUser(it)
                        if(result){
                            if(navController.backstack.entries.size == 1)
                                navController.setNewBackstack(listOf(navEntry(TopNavigationScreen.HomeScreen)))
                            else
                                navController.pop()
                        }
                        result
                    },
                    onManualLogin = {
                        navController.navigate(TopNavigationScreen.LoginScreen)
                        navController.setNewBackstack(listOf(navEntry(TopNavigationScreen.LoginScreen)))
                    }
                )
            }
        }
    }

}