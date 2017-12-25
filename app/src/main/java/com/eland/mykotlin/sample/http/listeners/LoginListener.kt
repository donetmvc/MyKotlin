package com.eland.mykotlin.sample.http.listeners

import com.eland.mykotlin.sample.dto.ApiResult
import com.eland.mykotlin.sample.dto.LoginInfo

/**
 * Created by liuwenbin on 17/12/25.
 */
interface ILoginListener {
    fun  onSuccess(body: ApiResult?)
    fun  onFailure()
}