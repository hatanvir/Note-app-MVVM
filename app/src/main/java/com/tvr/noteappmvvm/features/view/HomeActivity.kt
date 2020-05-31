package com.tvr.noteappmvvm.features.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.features.view.fragments.NoteListFragment
import com.tvr.noteappmvvm.features.view.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_note_list.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setFragment(NoteListFragment())
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationItemSelectedListener)
    }
    private val bottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when (item.itemId) {
            R.id.bottomNav_Notes -> setFragment(NoteListFragment())
            R.id.bottomNav_profile -> setFragment(ProfileFragment())
        }
        true
    }
    fun setFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer,fragment)
            .commit()
    }

}
