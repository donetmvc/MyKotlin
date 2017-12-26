package com.eland.mykotlin.sample.http

import com.eland.mykotlin.sample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by liuwenbin on 17/12/16.
 */

class BaseHttp {

    companion object {
        val baseUrl:String = "http://45.32.39.136:8081"

        val client: OkHttpClient? = OkHttpClient().newBuilder()
                // .cache(Cache(GetCreateFilePath.getPath(),10 * 10 * 1024)) //need permission
                .addInterceptor {
                    chain ->
                    val original = chain.request()
                    // See [http://developer.dribbble.com/v1/#authentication] for more information.
                    val requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
//                            .header("Authorization", "Bearer" + " " + mLastToken)  //add token
                            .method(original.method(), original.body())
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }).build()

        val retrofit: Retrofit? = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}