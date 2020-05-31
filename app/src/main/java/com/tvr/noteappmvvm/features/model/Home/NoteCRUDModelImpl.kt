package com.tvr.noteappmvvm.features.model.Home

import android.content.Context
import android.util.Log
import com.tvr.noteappmvvm.common.RequestCompleteListener
import com.tvr.noteappmvvm.features.model.Request.AddNoteRequest
import com.tvr.noteappmvvm.features.model.Request.UpdateNoteRequest
import com.tvr.noteappmvvm.features.model.Response.GetNotes
import com.tvr.noteappmvvm.network.ApiInterface
import com.tvr.noteappmvvm.network.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class NoteCRUDModelImpl(private val context: Context):NoteCRUDModel{

    override fun getList(id: Int, callback: RequestCompleteListener<GetNotes>) {
        Log.d("uuuu","calledGGG")
        val apiInterface:ApiInterface = RetrofitClient.getClient().create(ApiInterface::class.java);
        val call:Call<GetNotes> = apiInterface.getNotesById(id)
            call.enqueue(object :retrofit2.Callback<GetNotes>{
                override fun onFailure(call: Call<GetNotes>, t: Throwable) {
                    callback.onRequestFailed(t.localizedMessage!!)
                }

                override fun onResponse(call: Call<GetNotes>, response: Response<GetNotes>) {
                    if (response.body()!=null) callback.onRequestSuccess(response.body()!!)
                    else callback.onRequestFailed(response.message())
                }

            })
    }

    override fun addNote(addNoteRequest: AddNoteRequest, callback: RequestCompleteListener<ResponseBody>) {
        Log.d("uuuu","called")
        val apiInterface:ApiInterface = RetrofitClient.getClient().create(ApiInterface::class.java);
        val call:Call<ResponseBody> = apiInterface.addNote(addNoteRequest);
            call.enqueue(object :retrofit2.Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    callback.onRequestFailed(t.localizedMessage!!+"failed")
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.body()!=null) callback.onRequestSuccess(response.body()!!)
                    else callback.onRequestFailed(response.message()+"error"+response.code())
                }

            })
    }

    override fun updateNote(id: Int,updateNoteRequest: UpdateNoteRequest, callback: RequestCompleteListener<ResponseBody>) {
        Log.d("uuuu","calledUUU")
        val apiInterface:ApiInterface = RetrofitClient.getClient().create(ApiInterface::class.java);
        val call:Call<ResponseBody> = apiInterface.updateNote(id,updateNoteRequest)
        call.enqueue(object :retrofit2.Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body()!=null) callback.onRequestSuccess(response.body()!!)
                else callback.onRequestFailed(response.message())
            }

        })
    }

    override fun deleteNote(user_id:Int,note_id:Int,callback: RequestCompleteListener<ResponseBody>) {
        Log.d("uuuu","calledDDD")
        val apiInterface:ApiInterface = RetrofitClient.getClient().create(ApiInterface::class.java);
        val call: Call<ResponseBody> = apiInterface.deleteNote(user_id,note_id)
        call.enqueue(object :retrofit2.Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body()!=null) callback.onRequestSuccess(response.body()!!)
                else callback.onRequestFailed(response.message())
            }

        })
    }

}