package com.eland.mykotlin.sample.dto

/**
 * Created by liuwenbin on 17/12/21.
 */

data class ApiResult (val Success: Boolean, val Result: String, val Error: Error)

data class Error(val Code: Int, val Message: String, val Detail: String)