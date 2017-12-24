package com.eland.mykotlin.sample.dto

/**
 * Created by liuwenbin on 17/12/24.
 */

data class LoginInfo(val id: Int, val Name: String, val Password: String, val Detail: Detail)
data class Detail(val id: Int, val Email:String, val Address: String,val Phone: String)