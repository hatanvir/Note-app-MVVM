package com.tvr.noteappmvvm.features.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.features.view.fragments.NoteListFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        setFragment(NoteListFragment())

    }
    fun setFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer,fragment)
            .commit()
    }
}
