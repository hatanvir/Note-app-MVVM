package com.tvr.noteappmvvm.features.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.features.adapters.NoteListRecyclerViewAdapter
import com.tvr.noteappmvvm.features.model.Home.NoteListModel
import com.tvr.noteappmvvm.features.model.Home.NoteListModelImpl
import com.tvr.noteappmvvm.features.model.noteData
import com.tvr.noteappmvvm.features.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.fragment_note_list.*

class NoteListFragment : Fragment() {

    private lateinit var model:NoteListModel
    private lateinit var viewModel:NoteListViewModel

    private var arrayList = ArrayList<noteData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_note_list, container, false)
        model = NoteListModelImpl(requireActivity())

        viewModel = ViewModelProviders.of(requireActivity()).get(NoteListViewModel::class.java)
        getNotes()
        return v;
    }

    private fun getNotes() {
        noteRv.layoutManager = LinearLayoutManager(activity)
        val shared: SharedPreferences = activity!!.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val set: Set<String>? = shared.getStringSet("loginData", null)
        if (set!=null){
            val id = set!!.elementAt(0)
            viewModel.getNoteList(id.toInt(),model)
            viewModel.getNotesSuccessLiveData.observe(this, Observer { data->
                for (note in data.notes!!){
                    arrayList.add(noteData(note.id!!,note.subject!!,note.description!!,note.createdAt!!,note.updatedAt!!))
                    val adapter:NoteListRecyclerViewAdapter = NoteListRecyclerViewAdapter(activity!!,arrayList)
                    noteRv.adapter = adapter
                }
            })
        }
    }
}
