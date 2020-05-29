package com.tvr.noteappmvvm.features.model

import com.tvr.noteappmvvm.common.RequestCompleteListener
import com.tvr.noteappmvvm.features.model.Request.LoginRequest
import com.tvr.noteappmvvm.features.model.Request.RegisterRequest
import com.tvr.noteappmvvm.features.model.Response.LoginRes.Login
import okhttp3.ResponseBody

interface AuthModel {
    fun registerInfo(registerReq: RegisterRequest, callback: RequestCompleteListener<ResponseBody>)
    fun loginInfo(loginRequest: LoginRequest,callback:RequestCompleteListener<Login>)
}