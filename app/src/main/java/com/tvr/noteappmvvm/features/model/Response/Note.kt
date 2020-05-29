package com.tvr.noteappmvvm.features.model.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Note {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("subject")
    @Expose
    var subject: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

}