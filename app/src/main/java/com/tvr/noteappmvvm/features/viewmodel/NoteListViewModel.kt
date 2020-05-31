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
    val addNotePbLiveData = MutableLiveData<Boolean>()

    val updateNoteSuccessLiveData = MutableLiveData<ResponseBody>()
    val updateNoteFailedLiveData = MutableLiveData<String>()
    val updateNotePbLiveData = MutableLiveData<Boolean>()

    val deleteNoteSuccessLiveData = MutableLiveData<ResponseBody>()
    val deleteNoteFailedLiveData = MutableLiveData<String>()
    val deleteNotePbLiveData = MutableLiveData<Boolean>()

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
        addNotePbLiveData.postValue(true)
        model.addNote(request,object :RequestCompleteListener<ResponseBody>{
            override fun onRequestSuccess(data: ResponseBody) {
               addNoteSuccessLiveData.postValue(data)
                addNotePbLiveData.postValue(false)
            }

            override fun onRequestFailed(errorMessage: String) {
               addNoteFailedLiveData.postValue(errorMessage)
                addNotePbLiveData.postValue(false)
            }

        })
    }
    fun updateNote(id:Int,request: UpdateNoteRequest,model: NoteCRUDModel){
        updateNotePbLiveData.postValue(true)
        model.updateNote(id,request,object :RequestCompleteListener<ResponseBody>{
            override fun onRequestSuccess(data: ResponseBody) {
                updateNoteSuccessLiveData.postValue(data)
                updateNotePbLiveData.postValue(false)
            }

            override fun onRequestFailed(errorMessage: String) {
                updateNoteFailedLiveData.postValue(errorMessage)
                updateNotePbLiveData.postValue(false)
            }

        })
    }
    fun deleteNote(user_id:Int,note_id:Int,model: NoteCRUDModel){
        deleteNotePbLiveData.postValue(true)
        model.deleteNote(user_id,note_id,object :RequestCompleteListener<ResponseBody>{
            override fun onRequestSuccess(data: ResponseBody) {
                deleteNoteSuccessLiveData.postValue(data)
                deleteNotePbLiveData.postValue(false)
            }

            override fun onRequestFailed(errorMessage: String) {
                deleteNoteFailedLiveData.postValue(errorMessage)
                deleteNotePbLiveData.postValue(false)
            }

        })
    }
}