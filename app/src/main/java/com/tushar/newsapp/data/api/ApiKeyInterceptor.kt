package com.tushar.newsapp.data.api


import com.tushar.newsapp.di.NetworkApiKey
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(@NetworkApiKey private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder().header("X-Api-Key", apiKey)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}