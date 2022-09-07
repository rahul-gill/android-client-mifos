package org.mifos.client.android.data

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val token: () -> String,
    private val tenant: () -> String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val builder = request.newBuilder().header(Consts.HEADER_TENANT, tenant())
        token().let {
            if(it.isNotBlank())
                builder.header(Consts.HEADER_AUTH, "Basic $it")
        }
        return chain.proceed(builder.build())
    }
}

