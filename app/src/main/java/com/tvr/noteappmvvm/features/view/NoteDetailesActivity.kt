package com.tvr.noteappmvvm.features.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.Utils
import com.tvr.noteappmvvm.features.model.Home.NoteCRUDModel
import com.tvr.noteappmvvm.features.model.Home.NoteCRUDModelImpl
import com.tvr.noteappmvvm.features.model.Request.AddNoteRequest
import com.tvr.noteappmvvm.features.model.Request.UpdateNoteRequest
import com.tvr.noteappmvvm.features.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.activity_note_details.*

class NoteDetailsActivity : AppCompatActivity() {
    private lateinit var model:NoteCRUDModel
    private lateinit var viewModel:NoteListViewModel

    var id:String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        model = NoteCRUDModelImpl(applicationContext)
        viewModel = ViewModelProviders.of(this).get(NoteListViewModel::class.java)

        val intent: Intent = getIntent()

        try {
            id = intent.getStringExtra("id")
            val subject:String = intent.getStringExtra("subject")
            val description:String = intent.getStringExtra("description")
            subjectEt.setText(subject)
            descriptionEt.setText(description)
        }catch (e:Exception){}

        save_fab.setOnClickListener {
            if (id.length<=0){
                viewModel.addNote(AddNoteRequest(Utils.getUserId(this),
                    subjectEt.text.toString(),descriptionEt.text.toString()),model)
                viewModel.addNoteSuccessLiveData.observe(this, Observer { data->
                    Toast.makeText(this,"Note added",Toast.LENGTH_SHORT).show()
                    finish()
                })
                viewModel.addNoteFailedLiveData.observe(this, Observer { error->
                    Toast.makeText(this,"Failed to add note !"+error,Toast.LENGTH_SHORT).show()
                })
            }else{
                viewModel.updateNote(id.toInt(), UpdateNoteRequest(Utils.getUserId(this),
                    subjectEt.text.toString(),descriptionEt.text.toString()),model)

                viewModel.updateNoteSuccessLiveData.observe(this, Observer { data->
                    Toast.makeText(this,"Note updated",Toast.LENGTH_SHORT).show()
                })
                viewModel.updateNoteFailedLiveData.observe(this, Observer { error->
                    Toast.makeText(this,"Failed to update note !",Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}
