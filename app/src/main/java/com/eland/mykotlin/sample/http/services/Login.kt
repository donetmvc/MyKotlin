package com.eland.mykotlin.sample.http.services

import com.eland.mykotlin.sample.http.interfaces.LoginApiService

/**
 * Created by liuwenbin on 17/12/21.
 */


class Login {

    fun getLogin(userName: String, password: String) {
        val call = loginService.login(userName, password)
        val result = call.execute().body()
    }

    companion object {
        val loginService: LoginApiService = LoginApiService.retrofit!!.create(LoginApiService::class.java)
    }
}