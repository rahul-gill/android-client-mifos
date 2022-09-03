package org.mifos.client.android.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.mifos.client.android.R


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    username: String,
    onUsernameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onSwitchPasswordVisibility: () -> Unit,
    isLoading: Boolean,
    onLogin: () -> Unit,
    onLoginSuccess: () -> Unit,
    effectsFlow: Flow<LoginScreenEffects>
){

    var errorDialogState by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = Unit){
        effectsFlow.onEach {
            when (it) {
                is LoginScreenEffects.LoginFailed -> errorDialogState = true
                else -> onLoginSuccess()
            }
        }.collect()
    }


    if(errorDialogState)
        AlertDialog(
            onDismissRequest = { errorDialogState = false },
            title = { Text(text = "Provided credentials are incorrect", textAlign = TextAlign.Center) },
            text = { Text(text = "We can send you an email to help you get back into your account.", textAlign = TextAlign.Center) },
            confirmButton = {
                TextButton(onClick = { errorDialogState = false }) {
                    Text(text = "Send Email")
                }
            },
            dismissButton = {
                TextButton(onClick = { errorDialogState = false }) {
                    Text(text = "Try again", color = MaterialTheme.colorScheme.onSurface)
                }
            }
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(32.dp)
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = username,
                onValueChange = onUsernameChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                label = { Text(text = "Username") },
                trailingIcon = {
                    if (username.isNotEmpty())
                        IconButton(onClick = { onUsernameChange("") }) {
                            Icon(imageVector = Icons.Outlined.Clear, "Clear username")
                        }
                },
                singleLine = true,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = null
                    )
                },
                label = { Text(text = "Password") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onLogin()
                        keyboardController?.hide()
                    }
                ),
                trailingIcon = {
                    val image = if (isPasswordVisible)
                        Icons.Outlined.Visibility
                    else Icons.Outlined.VisibilityOff

                    val description =
                        if (isPasswordVisible) "Hide password" else "Show password"

                    IconButton(onClick = onSwitchPasswordVisibility) {
                        Icon(imageVector = image, description)
                    }
                },
                singleLine = true,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    keyboardController?.hide()
                    onLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(disabledContainerColor = MaterialTheme.colorScheme.primary)
            ) {
                if (isLoading)
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp
                    )
                else
                    Text(text = "Login")
            }
        }
    }
}