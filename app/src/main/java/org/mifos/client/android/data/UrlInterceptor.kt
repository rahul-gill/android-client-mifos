package org.mifos.client.android.data

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response

class UrlInterceptor(
    val url: () -> String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request.newBuilder()
                .url(request.url.toString().replace(ApiDefaults.BASE_URL, url()).toHttpUrlOrNull() ?: request.url)
                .build()
        )
    }
}