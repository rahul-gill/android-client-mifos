package org.mifos.client.android.ui.lock_screen

import android.content.Context
import android.content.Context.VIBRATOR_MANAGER_SERVICE
import android.content.Context.VIBRATOR_SERVICE
import android.os.*
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.mifos.client.android.R
import org.mifos.client.android.ui.components.LinkText
import org.mifos.client.android.ui.theme.size
import org.mifos.client.android.ui.theme.spacing
import org.mifos.client.android.ui.theme.typographyExtra

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassCodeScreen(
    enterPassCode: (String) -> Boolean = { it == "0000" },
    onManualLogin: () -> Unit = {},
    passcodeLength: Int = 4
) {
    var passcode by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val xShake = remember { Animatable(initialValue = 0.0F) }
    var isLogoutConfirmDialogShowing by mutableStateOf(false)

    if(isLogoutConfirmDialogShowing)
        AlertDialog(
            onDismissRequest = { isLogoutConfirmDialogShowing = false },
            title = { Text(text = stringResource(R.string.logout), textAlign = TextAlign.Center) },
            text = { Text(text = stringResource(R.string.logout_dialog_description), textAlign = TextAlign.Center) },
            confirmButton = {
                Button(onClick = {
                    onManualLogin()
                    isLogoutConfirmDialogShowing = false
                }) {
                    Text(text = stringResource(id = R.string.logout))
                }
            },
            dismissButton = {
                Button(onClick = { isLogoutConfirmDialogShowing = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = MaterialTheme.spacing.screenHorizontalPadding,
                    vertical = MaterialTheme.spacing.screenVerticalPadding
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(MaterialTheme.size.imageLarge)
            )
            PasscodeDigits(
                filledDots = passcode.length,
                totalDots = passcodeLength,
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.marginBetweenItemsLarge)
                    .offset(x = xShake.value.dp)
            )
            KeyButtons(
                enterKey = {
                    passcode += it
                    if (passcode.length == passcodeLength) {
                        val success = enterPassCode(passcode)
                        if (!success) {
                            vibrateFeedback(context)
                            scope.launch {
                                xShake.shake()
                                snackBarHostState.showSnackbar(
                                    message = context.getString(R.string.passcode_do_not_match),
                                    actionLabel = context.getString(R.string.ok),
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                        passcode = ""
                    }
                },
                clearKey = { passcode = passcode.dropLast(1) },
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.marginBetweenItemsLarge)
            )
            LinkText(
                text = stringResource(R.string.forget_passcode),
                onClick = { onManualLogin() }
            )
        }
    }
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassCodeCreateScreen(
    setPasscode: (String) -> Unit = { },
    passcodeLength: Int = 4
) {
    var passcode by remember { mutableStateOf("") }
    var passcodeConfirm by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val xShake = remember { Animatable(initialValue = 0.0F) }

    BackHandler(passcode.length == passcodeLength) {
        passcode = ""
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = MaterialTheme.spacing.screenHorizontalPadding,
                    vertical = MaterialTheme.spacing.screenVerticalPadding
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(MaterialTheme.size.imageLarge)
            ) {
                StepIndicator(
                    activeStep = if (passcode.length == passcodeLength) 1 else 0,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
                Text(
                    text =
                    if (passcode.length != passcodeLength) stringResource(R.string.create_passcode)
                    else stringResource(R.string.confirm_passcode),
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typographyExtra.heading
                )
            }
            PasscodeDigits(
                filledDots = if (passcode.length != passcodeLength) passcode.length else passcodeConfirm.length,
                totalDots = passcodeLength,
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.marginBetweenItemsLarge)
                    .offset(x = xShake.value.dp)
            )
            KeyButtons(
                enterKey = {
                    if (passcode.length != passcodeLength)
                        passcode += it
                    else
                        passcodeConfirm += it
                    if (passcode.length == passcodeLength && passcodeConfirm.length == passcodeLength) {
                        if (passcode == passcodeConfirm) {
                            setPasscode(passcode)
                        } else {
                            vibrateFeedback(context)
                            scope.launch {
                                xShake.shake()
                                snackBarHostState.showSnackbar(
                                    message = context.getString(R.string.passcode_do_not_match),
                                    actionLabel = context.getString(R.string.ok),
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                        passcode = ""
                        passcodeConfirm = ""
                    }
                },
                clearKey = {
                    if (passcode.length != passcodeLength)
                        passcode = passcode.dropLast(1)
                    else
                        passcodeConfirm = passcodeConfirm.dropLast(1)
                }
            )
        }
    }
}

@Composable
fun StepIndicator(
    modifier: Modifier = Modifier,
    activeStep: Int = 0
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 6.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        repeat(2) { step ->
            val isActiveStep = step <= activeStep
            val stepColor = animateColorAsState(
                if (isActiveStep) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surfaceVariant
            )

            Box(
                modifier = Modifier
                    .size(width = 72.dp, height = 4.dp)
                    .background(
                        color = stepColor.value,
                        shape = MaterialTheme.shapes.medium
                    )
            )
        }
    }
}

private suspend fun Animatable<Float, AnimationVector1D>.shake() {
    animateTo(
        targetValue = 0.dp.value,
        animationSpec = keyframes {
            0.0F at 0
            20.0F at 80
            -20.0F at 120
            10.0F at 160
            -10.0F at 200
            5.0F at 240
            0.0F at 280
        }
    )
}

@Composable
private fun PasscodeDigits(
    modifier: Modifier = Modifier,
    filledDots: Int = 0,
    totalDots: Int = 4
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 24.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        repeat(totalDots) { dot ->
            val isFilledDot = dot + 1 <= filledDots
            val dotColor = animateColorAsState(
                if (isFilledDot) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
            )

            Box(
                modifier = modifier
                    .size(size = 14.dp)
                    .background(
                        color = dotColor.value,
                        shape = CircleShape
                    )
            )
        }
    }
}


fun vibrateFeedback(
    context: Context,
    vibrateDuration: Long = 300
) {
    val vibration = VibrationEffect.createOneShot(
        vibrateDuration,
        VibrationEffect.DEFAULT_AMPLITUDE
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        (context.getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager).vibrate(
            CombinedVibration.createParallel(vibration)
        )
    } else {
        @Suppress("DEPRECATION")
        (context.getSystemService(VIBRATOR_SERVICE) as Vibrator).let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.vibrate(vibration)
            } else {
                it.vibrate(vibrateDuration)
            }
        }
    }
}