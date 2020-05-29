package com.tvr.noteappmvvm.features.model.Request

import com.google.gson.annotations.SerializedName

class UpdateNoteRequest {
    @SerializedName("user_id")
    val user_id = "";
    @SerializedName("subject")
    val subject = "";
    @SerializedName("description")
    val description = "";
}