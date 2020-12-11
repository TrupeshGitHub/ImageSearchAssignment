package com.assignment.imagesearch.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Client-ID 137cda6b5008a7c")
        return chain.proceed(requestBuilder.build())
    }
}