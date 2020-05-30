package com.tvr.noteappmvvm.features.model.Home

import android.view.ActionMode
import com.tvr.noteappmvvm.common.RequestCompleteListener
import com.tvr.noteappmvvm.features.model.Response.GetNotes
import okhttp3.ResponseBody

interface NoteListModel {
    fun getList(id:Int,callback: RequestCompleteListener<GetNotes>)
}