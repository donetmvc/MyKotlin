package com.eland.mykotlin.sample.http.services

import com.eland.mykotlin.sample.dto.LoginInfo
import com.eland.mykotlin.sample.http.interfaces.LoginApiService
import retrofit2.Call
import retrofit2.Response

/**
 * Created by liuwenbin on 17/12/21.
 */


class Login {

    fun getLogin(userName: String, password: String)  {
        val call = loginService.login(userName, password)

        call.enqueue(object : retrofit2.Callback<LoginInfo> {
            override fun onResponse(call: Call<LoginInfo>?, response: Response<LoginInfo>?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                val result = response?.body()
            }

            override fun onFailure(call: Call<LoginInfo>?, t: Throwable?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    companion object {
        val loginService: LoginApiService = LoginApiService.retrofit!!.create(LoginApiService::class.java)
    }
}