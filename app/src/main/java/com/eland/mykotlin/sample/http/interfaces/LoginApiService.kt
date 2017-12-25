package com.eland.mykotlin.sample.http.interfaces

import com.eland.mykotlin.sample.BuildConfig
import com.eland.mykotlin.sample.dto.ApiResult
import com.eland.mykotlin.sample.http.BaseHttp
import com.eland.mykotlin.sample.utils.GetCreateFilePath
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by liuwenbin on 17/12/16.
 */
interface LoginApiService {

    @GET("/api/v1/login/{name}/{pwd}")
    fun  login(@Path("name") name: String,  @Path("pwd") password: String)  : retrofit2.Call<ApiResult>

    @GET("/api/v1/login/{name}/{pwd}")
    fun observableLogin(@Path("name") name: String,  @Path("pwd") password: String)  : Observable<ApiResult>

    companion object {
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
                .baseUrl(BaseHttp.baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}