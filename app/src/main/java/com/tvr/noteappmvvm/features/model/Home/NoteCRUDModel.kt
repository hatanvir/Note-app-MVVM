package com.tvr.noteappmvvm.features.model.Home

import android.view.ActionMode
import com.tvr.noteappmvvm.common.RequestCompleteListener
import com.tvr.noteappmvvm.features.model.Request.AddNoteRequest
import com.tvr.noteappmvvm.features.model.Request.UpdateNoteRequest
import com.tvr.noteappmvvm.features.model.Response.GetNotes
import okhttp3.ResponseBody

interface NoteCRUDModel {
    fun getList(id:Int,callback: RequestCompleteListener<GetNotes>)
    fun addNote(addNoteRequest: AddNoteRequest,callback: RequestCompleteListener<ResponseBody>)
    fun updateNote(id: Int,updateNoteRequest: UpdateNoteRequest,callback: RequestCompleteListener<ResponseBody>)
    fun deleteNote(user_id:Int,note_id:Int,callback: RequestCompleteListener<ResponseBody>)
}