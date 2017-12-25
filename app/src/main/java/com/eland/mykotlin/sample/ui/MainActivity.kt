package com.eland.mykotlin.sample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eland.mykotlin.sample.R
import com.eland.mykotlin.sample.dto.ApiResult
import com.eland.mykotlin.sample.http.interfaces.LoginApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by liuwenbin on 17/12/25.
 */


class MainActivity: AppCompatActivity() {

    private var loginService: LoginApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginService = LoginApiService.retrofit!!.create(LoginApiService::class.java)

        setContentView(R.layout.activity_main)

        button.setOnClickListener{ v ->
            loginService!!.observableLogin("123", "123")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ t: ApiResult? ->
                        if(t!!.Success) {

                        }
                        else textView.text = t!!.Error.Message
                    })
        }
    }

}