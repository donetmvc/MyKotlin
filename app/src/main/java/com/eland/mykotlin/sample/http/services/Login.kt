package com.eland.mykotlin.sample.http.services

import com.eland.mykotlin.sample.dto.ApiResult
import com.eland.mykotlin.sample.dto.LoginInfo
import com.eland.mykotlin.sample.http.BaseHttp
import com.eland.mykotlin.sample.http.interfaces.LoginApiService
import com.eland.mykotlin.sample.http.listeners.ILoginListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

/**
 * Created by liuwenbin on 17/12/21.
 */


class Login {

    fun getLogin(userName: String, password: String, listener: ILoginListener)  {
        val call = loginService.login(userName, password)
//        observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe()
        call.enqueue(object : retrofit2.Callback<ApiResult> {
            override fun onResponse(call: Call<ApiResult>?, response: Response<ApiResult>?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                listener.onSuccess(response?.body())
                //val result = response?.body()
            }

            override fun onFailure(call: Call<ApiResult>?, t: Throwable?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                listener.onFailure()
            }
        })
    }

    fun getAbservableLogin(userName: String, password: String, listener: ILoginListener) {
        loginService.observableLogin(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: ApiResult? ->
                    listener.onSuccess(t)
                }, {
                    listener.onFailure()
                })
    }

    companion object {
        val loginService: LoginApiService = BaseHttp.retrofit!!.create(LoginApiService::class.java)
    }
}