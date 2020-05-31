package com.tvr.noteappmvvm

import android.content.Context
import android.content.SharedPreferences

class Utils {
    companion object{
        fun  getUserId(context: Context): String {
            val shared: SharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE)
            val set: Set<String>? = shared.getStringSet("loginData", null)
            return set!!.elementAt(0)
        }
    }
}