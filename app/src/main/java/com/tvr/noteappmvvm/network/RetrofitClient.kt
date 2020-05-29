package com.tvr.noteappmvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private var retrofit:Retrofit? = null;
    fun getClient():Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl("https://notes-app555.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit !!;
    }
}