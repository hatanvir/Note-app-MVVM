package com.tvr.noteappmvvm.network

import com.tvr.noteappmvvm.features.model.Request.AddNoteRequest
import com.tvr.noteappmvvm.features.model.Request.LoginRequest
import com.tvr.noteappmvvm.features.model.Request.RegisterRequest
import com.tvr.noteappmvvm.features.model.Request.UpdateNoteRequest
import com.tvr.noteappmvvm.features.model.Response.GetNotes
import com.tvr.noteappmvvm.features.model.Response.LoginRes.Login
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST("/api/register")
    fun register(@Body request: RegisterRequest): Call<ResponseBody>

    @POST("/api/login")
    fun login(@Body request: LoginRequest):Call<Login>

    @POST("/api/addNote")
    fun addNote(@Body request: AddNoteRequest):Call<ResponseBody>

    @POST("/api/updateNote/{id}")
    fun updateNote(@Path("id") id:Int?,@Body request: UpdateNoteRequest):Call<ResponseBody>

    @GET("/api/getNotesByUerId/{id}")
    fun getNotesById(@Path("id") id:Int?):Call<GetNotes>

    @POST("/api/deleteNote/{user_id}/{note_id}")
    fun deleteNote(
        @Path("user_id") user_id:Int?,
        @Path("note_id") note_id:Int?):Call<ResponseBody>

}