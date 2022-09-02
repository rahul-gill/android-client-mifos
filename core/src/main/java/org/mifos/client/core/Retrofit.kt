package org.mifos.client.core

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.SandwichInitializer.sandwichScope
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


private fun buildOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .callTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(
            HttpLoggingInterceptor {
                Log.d("Retrofit", it)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            },
        )
        .build()
}

@OptIn(ExperimentalSerializationApi::class)
val retrofitClient: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("https://mobile.mifos.io/fineract-provider/api/v1/self/")
        .client(buildOkHttpClient())
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .build()
}
