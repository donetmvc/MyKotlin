package com.eland.mykotlin.sample.http

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by liuwenbin on 17/12/21.
 */

class LastFmRequestInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().newBuilder()
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("format", "json")
                .build()

        val newRequest = request.newBuilder()
                .url(url)
                .build()

        return chain.proceed(newRequest)
    }
}

