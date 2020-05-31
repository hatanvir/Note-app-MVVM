package com.tvr.noteappmvvm.features.model.Request

import com.google.gson.annotations.SerializedName

class AddNoteRequest(user_id: String, subject: String, description: String) {
    @SerializedName("user_id")
    val user_id = user_id
    @SerializedName("subject")
    val subject = subject
    @SerializedName("description")
    val description = description
}