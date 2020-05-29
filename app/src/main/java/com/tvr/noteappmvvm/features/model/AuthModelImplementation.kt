package com.tvr.noteappmvvm.features.model

import android.content.Context
import android.util.Log
import com.tvr.noteappmvvm.common.RequestCompleteListener
import com.tvr.noteappmvvm.features.model.Request.LoginRequest
import com.tvr.noteappmvvm.features.model.Request.RegisterRequest
import com.tvr.noteappmvvm.features.model.Response.LoginRes.Login
import com.tvr.noteappmvvm.network.ApiInterface
import com.tvr.noteappmvvm.network.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class AuthModelImplementation(private val context: Context) :AuthModel{

    override fun registerInfo(registerReq: RegisterRequest, callback: RequestCompleteListener<ResponseBody>) {
        val apiInterface:ApiInterface = RetrofitClient.getClient().create(ApiInterface::class.java)

        val call: Call<ResponseBody> = apiInterface.register(registerReq)
        call.enqueue(object : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseBody>,response: Response<ResponseBody>) {
                if (response.body() != null)
                    callback.onRequestSuccess(response.body()!!) //let presenter know the weather information data
                else
                    callback.onRequestFailed("failed"+response.code())
            }

        })
    }

     override fun loginInfo(loginRequest: LoginRequest, callback: RequestCompleteListener<Login>) {
        RetrofitClient.getClient().create(ApiInterface::class.java)
            .login(loginRequest)
            .enqueue(object :Callback<Login>{
                override fun onFailure(call: Call<Login>, t: Throwable) {
                 callback.onRequestFailed(t.localizedMessage)
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                   if (response.body()!=null) callback.onRequestSuccess(response.body()!!)
                   else callback.onRequestFailed(response.message())
                }

            })
     }
 }