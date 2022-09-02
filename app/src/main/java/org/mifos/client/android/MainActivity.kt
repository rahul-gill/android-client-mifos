package org.mifos.client.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.toFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.mifos.client.android.auth.LoginScreen
import org.mifos.client.android.ui.theme.MifosAndroidClientTheme
import org.mifos.client.core.auth.AuthPostRequest
import org.mifos.client.core.auth.AuthService
import org.mifos.client.core.retrofitClient

class MainActivity : ComponentActivity() {

    val authApi = retrofitClient.create(AuthService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MifosAndroidClientTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(
                        onLogin = { username, password ->
                            lifecycleScope.launch {
                                authApi.authenticate(AuthPostRequest(username, password))
                                    .onError {
                                        println("Error: ${this.errorBody}")
                                    }.onException {
                                        println("onException: ${this.message} ${this.exception}")
                                    }.onSuccess {
                                        println("data: $data")
                                    }
                                    .toFlow().collectLatest {
                                        println("Response: $it")
                                    }
                            }
                        }
                    )
                }
            }
        }
    }
}