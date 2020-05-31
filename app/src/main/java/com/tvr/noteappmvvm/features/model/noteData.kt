package com.tvr.noteappmvvm.features.model

import android.os.Parcelable

data class noteData(
    val id:Int,
    val subject:String,
    val description:String,
    val created_at:String,
    val updated_at:String,
    val color: String
)