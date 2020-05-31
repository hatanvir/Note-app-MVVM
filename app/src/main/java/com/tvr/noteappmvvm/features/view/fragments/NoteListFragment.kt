package com.tvr.noteappmvvm.features.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.Utils
import com.tvr.noteappmvvm.features.adapters.NoteListRecyclerViewAdapter
import com.tvr.noteappmvvm.features.model.Home.NoteCRUDModel
import com.tvr.noteappmvvm.features.model.Home.NoteCRUDModelImpl
import com.tvr.noteappmvvm.features.model.noteData
import com.tvr.noteappmvvm.features.view.NoteDetailsActivity
import com.tvr.noteappmvvm.features.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlinx.android.synthetic.main.fragment_note_list.view.*
import java.util.*
import kotlin.collections.ArrayList

class NoteListFragment : Fragment() {

    @BindView(R.id.noteRv)
    lateinit var noteRv:RecyclerView

    private lateinit var model:NoteCRUDModel
    private lateinit var viewModel:NoteListViewModel

    private var arrayList = ArrayList<noteData>()
    private val colorList = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_note_list, container, false)
        ButterKnife.bind(this,v)
        model = NoteCRUDModelImpl(requireActivity())

        viewModel = ViewModelProviders.of(requireActivity()).get(NoteListViewModel::class.java)
        colorList.add("#FF3333")
        colorList.add("#2983F6")
        colorList.add("#F629C4")
        colorList.add("#c77de7")
        colorList.add("#9029F6")

        viewModel.notesLoadingProgressBarLiveData.observe(this, Observer {isLoading->
            if (isLoading) noteLoadingPb.visibility = View.VISIBLE
            else noteLoadingPb.visibility = View.GONE
        })
        v.fab.setOnClickListener {
            startActivity(Intent(activity,NoteDetailsActivity::class.java))
        }
        return v;
    }

    override fun onResume() {
        super.onResume()
        getNotes()
    }

    private fun getNotes() {

        arrayList.clear()
        noteRv.layoutManager = LinearLayoutManager(activity)
        val id = Utils.getUserId(activity!!)
        if (id!=null){
            viewModel.getNoteList(id.toInt(),model)
            viewModel.getNotesSuccessLiveData.observe(this, Observer { data->
                arrayList.clear()
                for (note in data.notes!!){
                    arrayList.add(noteData(note.id!!,note.subject!!,note.description!!,note.createdAt!!,note.updatedAt!!,
                        colorList.random()))
                }
                Collections.sort(arrayList,
                    Comparator<noteData> { a, b -> a.subject.compareTo(b.subject) })

                val adapter:NoteListRecyclerViewAdapter = NoteListRecyclerViewAdapter(activity!!,arrayList)
                noteRv.adapter = adapter
            })
        }
    }
}
