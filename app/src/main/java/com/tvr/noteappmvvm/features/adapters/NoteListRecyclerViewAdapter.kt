package com.tvr.noteappmvvm.features.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.Utils
import com.tvr.noteappmvvm.features.model.Home.NoteCRUDModel
import com.tvr.noteappmvvm.features.model.Home.NoteCRUDModelImpl
import com.tvr.noteappmvvm.features.model.noteData
import com.tvr.noteappmvvm.features.view.NoteDetailsActivity
import com.tvr.noteappmvvm.features.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.activity_note_details.*
import kotlinx.android.synthetic.main.dialog_option_bottomsheet.*
import kotlinx.android.synthetic.main.note_recyclerview_shape.view.*
import java.lang.Exception
import kotlin.collections.ArrayList

class NoteListRecyclerViewAdapter(val context: Context,val arrayList:ArrayList<noteData>):
    RecyclerView.Adapter<NoteListRecyclerViewAdapter.NoteViewHolder>(){

    private lateinit var viewModel: NoteListViewModel
    private lateinit var model: NoteCRUDModel

     class NoteViewHolder(view:View):RecyclerView.ViewHolder(view){
         val subject = view.subjectOnRv
         val date = view.rvDate
         val optionsIcon = view.rvOptionIm
         val rvCardView = view.rvCardview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListRecyclerViewAdapter.NoteViewHolder {
        model= NoteCRUDModelImpl(context)
        viewModel = ViewModelProviders.of(context as FragmentActivity).get(NoteListViewModel::class.java)

        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_recyclerview_shape,parent,false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: NoteListRecyclerViewAdapter.NoteViewHolder, position: Int) {
        holder.subject.setText(arrayList[position].subject)
        holder.date.setText(arrayList[position].updated_at)
        holder.itemView.setOnClickListener {
          goToEditActivity(position)
        }
        holder.optionsIcon.setOnClickListener {
            showBottomSheet(position);
        }
        holder.rvCardView.setCardBackgroundColor(Color.parseColor(arrayList[position].color))


    }
    fun goToEditActivity(position: Int){
        context.startActivity(Intent(context,NoteDetailsActivity::class.java)
            .putExtra("id",arrayList[position].id.toString())
            .putExtra("subject",arrayList[position].subject)
            .putExtra("description",arrayList[position].description))
    }

    private fun showBottomSheet(position: Int) {
        val b:BottomSheetDialog = BottomSheetDialog(context)
        b.setContentView(R.layout.dialog_option_bottomsheet)
        b.deleteIM.setOnClickListener {
            viewModel.deleteNote(Utils.getUserId(context).toInt(),arrayList[position].id.toString().toInt(),model)
            viewModel.deleteNoteSuccessLiveData.observe(context as FragmentActivity, Observer {data->
                try {
                    arrayList.removeAt(position)
                    notifyDataSetChanged()
                    Toast.makeText(context,"Note deleted",Toast.LENGTH_SHORT).show()
                    b.dismiss()
                }catch (e:Exception){}
            })
        }
        b.editIm.setOnClickListener {
            goToEditActivity(position)
            b.dismiss()
        }
        b.show()
    }

}