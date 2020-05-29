package com.tvr.noteappmvvm.common

interface RequestCompleteListener<T> {
    fun onRequestSuccess(data:T)
    fun onRequestFailed(errorMessage:String)
}