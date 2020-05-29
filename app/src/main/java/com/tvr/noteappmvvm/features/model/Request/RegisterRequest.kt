package com.tvr.noteappmvvm.features.model.Request

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword

class RegisterRequest(name:String,email: String,password:String,password_confirmation: String) {
    @SerializedName("name")
    val name = name;
    @SerializedName("email")
    val email = email;
    @SerializedName("password")
    val password = password;
    @SerializedName("password_confirmation")
    val password_confirmation = password_confirmation;

}