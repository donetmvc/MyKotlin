package com.eland.mykotlin.sample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eland.mykotlin.sample.R
import com.eland.mykotlin.sample.dto.ApiResult
import com.eland.mykotlin.sample.http.interfaces.LoginApiService
import com.eland.mykotlin.sample.http.listeners.ILoginListener
import com.eland.mykotlin.sample.http.services.Login
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by liuwenbin on 17/12/25.
 */


class MainActivity: AppCompatActivity(), ILoginListener {
//    private var loginService: LoginApiService? = null

    private var login: Login ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //loginService = Login.loginService //.retrofit!!.create(LoginApiService::class.java)

        setContentView(R.layout.activity_main)
        login = Login()

        button.setOnClickListener{ _ ->
//            loginService!!.observableLogin("123", "123")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ t: ApiResult? ->
//                        if(t!!.Success) {
//
//                        }
//                        else textView.text = t!!.Error.Message
//                    })
            login!!.getAbservableLogin("123", "123", this)
        }
    }

    override fun onSuccess(body: ApiResult?) {
        if(body!!.Success) {

        }
        else textView.text = body!!.Error.Message
    }

    override fun onFailure() {

    }

}