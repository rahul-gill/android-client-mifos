package org.mifos.client.android.data

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mifos.client.android.data.local_prefs.PrefsManager
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


@OptIn(ExperimentalSerializationApi::class)
fun buildRetrofitClient(
    prefsManager: PrefsManager
): Retrofit {
    val authInterceptor = AuthInterceptor(
        token = { prefsManager.token },
        tenant = { prefsManager.tenant.ifBlank { ApiDefaults.HEADER_TENANT_DEFAULT_VALUE } }
    )
    val urlInterceptor = UrlInterceptor(
        url = { prefsManager.instanceBaseUrl.ifBlank { ApiDefaults.BASE_URL } }
    )

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .callTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(authInterceptor)
        .addInterceptor(urlInterceptor)
        .addInterceptor(
            HttpLoggingInterceptor {
                Log.d("Retrofit", it)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            },
        )
        .build()

    val json = Json { ignoreUnknownKeys = true }

    return Retrofit.Builder()
        .baseUrl(ApiDefaults.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .build()
}
