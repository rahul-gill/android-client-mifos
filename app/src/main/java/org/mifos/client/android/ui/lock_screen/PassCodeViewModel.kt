package org.mifos.client.android.ui.lock_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mifos.client.android.data.local_prefs.PrefsManager
import javax.inject.Inject

@HiltViewModel
class PassCodeViewModel @Inject constructor(
    val manager: PrefsManager
) : ViewModel() {
    fun logout(){
        manager.passcode = ""
        manager.token = ""
        manager.userId = -1
        manager.username = ""
    }
}