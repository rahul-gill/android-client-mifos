package org.mifos.client.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

class ColorsSchemeExtra(
    success: Color,
    onSuccess: Color,
    successContainer: Color,
    onSuccessContainer: Color,
    warning: Color,
    onWarning: Color,
    warningContainer: Color,
    onWarningContainer: Color,
    isLight: Boolean
) {
    var success by mutableStateOf(success)
        private set
    var onSuccess by mutableStateOf(onSuccess)
        private set
    var successContainer by mutableStateOf(successContainer)
        private set
    var onSuccessContainer by mutableStateOf(onSuccessContainer)
        private set
    var warning by mutableStateOf(warning)
        private set
    var onWarning by mutableStateOf(onWarning)
        private set
    var warningContainer by mutableStateOf(warningContainer)
        private set
    var onWarningContainer by mutableStateOf(onWarningContainer)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        success: Color = this.success,
        onSuccess: Color = this.onSuccess,
        successContainer: Color = this.successContainer,
        onSuccessContainer: Color = this.onSuccessContainer,
        warning: Color = this.warning,
        onWarning: Color = this.onWarning,
        warningContainer: Color = this.warningContainer,
        onWarningContainer: Color = this.onWarningContainer,
        isLight: Boolean = this.isLight
    ): ColorsSchemeExtra = ColorsSchemeExtra(
        success,
        onSuccess,
        successContainer,
        onSuccessContainer,
        warning,
        onWarning,
        warningContainer,
        onWarningContainer,
        isLight
    )

    fun updateColorSchemeFrom(other: ColorsSchemeExtra) {
        success = other.success
        onSuccess = other.onSuccess
        successContainer = other.successContainer
        onSuccessContainer = other.onSuccessContainer
        warning = other.warning
        onWarning = other.onWarning
        warningContainer = other.warningContainer
        onWarningContainer = other.onWarningContainer
    }
}

fun lightColorSchemeExtra(
    success: Color = light_success,
    onSuccess: Color = light_onsuccess,
    successContainer: Color = light_successContainer,
    onSuccessContainer: Color = light_onsuccessContainer,
    warning: Color = light_warning,
    onWarning: Color = light_onwarning,
    warningContainer: Color = light_warningContainer,
    onWarningContainer: Color = light_onwarningContainer
): ColorsSchemeExtra = ColorsSchemeExtra(
    success,
    onSuccess,
    successContainer,
    onSuccessContainer,
    warning,
    onWarning,
    warningContainer,
    onWarningContainer,
    true
)

fun darkColorSchemeExtra(
    success: Color = dark_success,
    onSuccess: Color = dark_onsuccess,
    successContainer: Color = dark_successContainer,
    onSuccessContainer: Color = dark_onsuccessContainer,
    warning: Color = dark_warning,
    onWarning: Color = dark_onwarning,
    warningContainer: Color = dark_warningContainer,
    onWarningContainer: Color = dark_onwarningContainer
): ColorsSchemeExtra = ColorsSchemeExtra(
    success,
    onSuccess,
    successContainer,
    onSuccessContainer,
    warning,
    onWarning,
    warningContainer,
    onWarningContainer,
    false
)

internal val LocalColorSchemeExtra = staticCompositionLocalOf { lightColorSchemeExtra() }

val MaterialTheme.colorSchemeExtra: ColorsSchemeExtra
    @Composable
    @ReadOnlyComposable
    get() = LocalColorSchemeExtra.current