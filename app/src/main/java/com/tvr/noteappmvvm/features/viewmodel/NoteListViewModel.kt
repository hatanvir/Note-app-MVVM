package com.tvr.noteappmvvm.features.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tvr.noteappmvvm.common.RequestCompleteListener
import com.tvr.noteappmvvm.features.model.Home.NoteCRUDModel
import com.tvr.noteappmvvm.features.model.Request.AddNoteRequest
import com.tvr.noteappmvvm.features.model.Request.UpdateNoteRequest
import com.tvr.noteappmvvm.features.model.Response.GetNotes
import okhttp3.ResponseBody

class NoteListViewModel:ViewModel() {
    val notesLoadingProgressBarLiveData = MutableLiveData<Boolean>()
    val getNotesSuccessLiveData = MutableLiveData<GetNotes>()
    val getNotesFailedLiveData = MutableLiveData<String>()

    val addNoteSuccessLiveData = MutableLiveData<ResponseBody>()
    val addNoteFailedLiveData = MutableLiveData<String>()

    val updateNoteSuccessLiveData = MutableLiveData<ResponseBody>()
    val updateNoteFailedLiveData = MutableLiveData<String>()

    val deleteNoteSuccessLiveData = MutableLiveData<ResponseBody>()
    val deleteNoteFailedLiveData = MutableLiveData<String>()

    fun getNoteList(id:Int,model: NoteCRUDModel){
        notesLoadingProgressBarLiveData.postValue(true)
        model.getList(id,object :RequestCompleteListener<GetNotes>{
            override fun onRequestSuccess(data: GetNotes) {
                getNotesSuccessLiveData.postValue(data)
                notesLoadingProgressBarLiveData.postValue(false)
            }

            override fun onRequestFailed(errorMessage: String) {
                getNotesFailedLiveData.postValue(errorMessage)
                notesLoadingProgressBarLiveData.postValue(false)
            }

        })
    }

    fun addNote(request: AddNoteRequest,model: NoteCRUDModel){
        model.addNote(request,object :RequestCompleteListener<ResponseBody>{
            override fun onRequestSuccess(data: ResponseBody) {
               addNoteSuccessLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {
               addNoteFailedLiveData.postValue(errorMessage)
            }

        })
    }
    fun updateNote(id:Int,request: UpdateNoteRequest,model: NoteCRUDModel){
        model.updateNote(id,request,object :RequestCompleteListener<ResponseBody>{
            override fun onRequestSuccess(data: ResponseBody) {
                updateNoteSuccessLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                updateNoteFailedLiveData.postValue(errorMessage)
            }

        })
    }
    fun deleteNote(user_id:Int,note_id:Int,model: NoteCRUDModel){
        model.deleteNote(user_id,note_id,object :RequestCompleteListener<ResponseBody>{
            override fun onRequestSuccess(data: ResponseBody) {
                deleteNoteSuccessLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                deleteNoteFailedLiveData.postValue(errorMessage)
            }

        })
    }
}