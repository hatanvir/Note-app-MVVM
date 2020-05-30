package com.tvr.noteappmvvm.features.model.Home

import android.content.Context
import com.tvr.noteappmvvm.common.RequestCompleteListener
import com.tvr.noteappmvvm.features.model.Response.GetNotes
import com.tvr.noteappmvvm.network.ApiInterface
import com.tvr.noteappmvvm.network.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class NoteListModelImpl(private val context: Context):NoteListModel{
    override fun getList(id: Int, callback: RequestCompleteListener<GetNotes>) {
        val apiInterface:ApiInterface = RetrofitClient.getClient().create(ApiInterface::class.java);
        val call:Call<GetNotes> = apiInterface.getNotesById(id)
            call.enqueue(object :retrofit2.Callback<GetNotes>{
                override fun onFailure(call: Call<GetNotes>, t: Throwable) {
                    callback.onRequestFailed(t.localizedMessage)
                }

                override fun onResponse(call: Call<GetNotes>, response: Response<GetNotes>) {
                    if (response.body()!=null) callback.onRequestSuccess(response.body()!!)
                    else callback.onRequestFailed(response.message())
                }

            })
    }

}