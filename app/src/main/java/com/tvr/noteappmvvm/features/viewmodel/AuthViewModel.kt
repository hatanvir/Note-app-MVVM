package com.tvr.noteappmvvm.features.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tvr.noteappmvvm.common.RequestCompleteListener
import com.tvr.noteappmvvm.features.model.Auth.AuthModel
import com.tvr.noteappmvvm.features.model.Request.LoginRequest
import com.tvr.noteappmvvm.features.model.Request.RegisterRequest
import com.tvr.noteappmvvm.features.model.Response.LoginRes.Login
import okhttp3.ResponseBody

class AuthViewModel: ViewModel() {
    val registerSuccessLiveData = MutableLiveData<ResponseBody>()
    val registerFailedLiveData = MutableLiveData<String>()

    val progressbarLiveData = MutableLiveData<Boolean>()

    val loginSuccessLiveData = MutableLiveData<Login>()
    val loginFailedLiveData = MutableLiveData<String>()

    val loginProgressbarLiveData = MutableLiveData<Boolean>()

    fun registerInfo(request: RegisterRequest,model: AuthModel){
        progressbarLiveData.postValue(true)
        model.registerInfo(request,object :RequestCompleteListener<ResponseBody>{
            override fun onRequestSuccess(data: ResponseBody) {
                registerSuccessLiveData.postValue(data)
                progressbarLiveData.postValue(false)
            }

            override fun onRequestFailed(errorMessage: String) {
                registerFailedLiveData.postValue(errorMessage)
                progressbarLiveData.postValue(false)
            }

        })
    }
    fun loginInfo(request: LoginRequest,model: AuthModel){
        loginProgressbarLiveData.postValue(true)
        model.loginInfo(request,object :RequestCompleteListener<Login>{
            override fun onRequestSuccess(data: Login) {
                loginSuccessLiveData.postValue(data)
                loginProgressbarLiveData.postValue(false)
            }

            override fun onRequestFailed(errorMessage: String) {
                loginFailedLiveData.postValue(errorMessage)
                loginProgressbarLiveData.postValue(false)
            }

        })
    }
}