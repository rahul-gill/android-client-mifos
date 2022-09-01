package org.mifos.client.core

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.ResponseConverter
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.reflect.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

typealias ApiResponseFlow<T> = Flow<ApiResponse<T>>

val ktorfit by lazy {
    Ktorfit.Builder()
        .baseUrl("https://swapi.dev/api/")
        .httpClient(
            HttpClient {
                install(ContentNegotiation) {
                    json(Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
            }
        )
        .responseConverter(MyResponseConverter()).build()

}


class MyResponseConverter : ResponseConverter {

    override fun supportedType(returnTypeName: String): Boolean {
        return returnTypeName == "kotlinx.coroutines.flow.Flow"
    }

    override fun <PRequest : Any> wrapResponse(
        returnTypeName: String,
        requestFunction: suspend () -> Pair<TypeInfo, HttpResponse>
    ): Any {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val (info, response) = requestFunction()
                emit(ApiResponse.Success(response.call.body(info)))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }
}

sealed class ApiResponse<T>{
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Error<T>( val exception: Exception? = null, val errorMessage: String? = null): ApiResponse<T>()
    object Loading: ApiResponse<Any>()
}