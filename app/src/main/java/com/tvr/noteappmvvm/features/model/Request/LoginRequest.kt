package com.tvr.noteappmvvm.features.model.Request

import com.google.gson.annotations.SerializedName

class LoginRequest(email:String,password:String) {
    @SerializedName("email")
    val email = email;
    @SerializedName("password")
    val password = password;
}