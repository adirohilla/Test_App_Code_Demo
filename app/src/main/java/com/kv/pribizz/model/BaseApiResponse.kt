package com.kv.pribizz.model

import com.kv.pribizz.BuildConfig
import com.kv.pribizz.utils.NetworkResult
import retrofit2.Response
import java.net.SocketTimeoutException

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
            when (e) {
                is SocketTimeoutException -> {
                    return error("Response time out!")
                }
                else -> {
                    return error(e.message ?: e.toString())
                }
            }
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(errorMessage)

}