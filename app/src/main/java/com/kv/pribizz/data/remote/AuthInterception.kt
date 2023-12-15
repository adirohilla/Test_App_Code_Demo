package com.kv.pribizz.data.remote

import android.content.Context
import android.content.Intent
import com.kv.pribizz.datastoremanager.DataStoreManager
import com.kv.pribizz.ui.login.LoginActivity
import com.kv.pribizz.utils.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterception @Inject constructor(
    val context: Context,
    val dataStoreManager: DataStoreManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val user = runBlocking { dataStoreManager.getUser().first() }
        val token = user?.token
        val requestBuilder = chain.request().newBuilder()
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", token)
        }
        val request = requestBuilder.build()
        val response = chain.proceed(request)
        if (!response.isSuccessful && response.code == 401) {
            GlobalScope.launch {
                dataStoreManager.saveUser(null)
            }
            val intent = Intent(context, LoginActivity::class.java).apply {
                putExtra(Constants.UNAUTHORIZED, true)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
        return response
    }
}