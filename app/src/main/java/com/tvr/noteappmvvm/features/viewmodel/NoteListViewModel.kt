package com.tvr.noteappmvvm.features.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tvr.noteappmvvm.common.RequestCompleteListener
import com.tvr.noteappmvvm.features.model.Home.NoteListModel
import com.tvr.noteappmvvm.features.model.Response.GetNotes

class NoteListViewModel:ViewModel() {
    val notesLoadingProgressBarLiveData = MutableLiveData<Boolean>()
    val getNotesSuccessLiveData = MutableLiveData<GetNotes>()
    val getNotesFailedLiveData = MutableLiveData<String>()

    fun getNoteList(id:Int,model: NoteListModel){
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
}