package com.tvr.noteappmvvm.features.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.features.model.noteData
import kotlinx.android.synthetic.main.note_recyclerview_shape.view.*

class NoteListRecyclerViewAdapter(val context: Context,val arrayList:ArrayList<noteData>): RecyclerView.Adapter<NoteListRecyclerViewAdapter.NoteViewHolder>() {
     class NoteViewHolder(view:View):RecyclerView.ViewHolder(view){
         val subject = view.subjectOnRv
         val date = view.rvDate
         val optionsIcon = view.rvOptionIm
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListRecyclerViewAdapter.NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_recyclerview_shape,parent,false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: NoteListRecyclerViewAdapter.NoteViewHolder, position: Int) {
        holder.subject.setText(arrayList[position].subject)
        holder.date.setText(arrayList[position].updated_at)
        holder.optionsIcon.setOnClickListener {
            Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()
        }
    }
}