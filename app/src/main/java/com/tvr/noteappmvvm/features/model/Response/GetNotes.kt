package com.tvr.noteappmvvm.features.model.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetNotes {
    @SerializedName("notes")
    @Expose
    var notes: List<Note>? =
        null

    @SerializedName("0")
    @Expose
    private var _0: Int? = null

    fun get0(): Int? {
        return _0
    }

    fun set0(_0: Int?) {
        this._0 = _0
    }
}