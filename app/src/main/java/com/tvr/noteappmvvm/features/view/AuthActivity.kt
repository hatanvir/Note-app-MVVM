package com.tvr.noteappmvvm.features.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.features.view.fragments.RegisterFragment as RegisterFragment1

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportActionBar!!.hide()
        setfragment(RegisterFragment1())

        Toast.makeText(this,"here",Toast.LENGTH_SHORT).show()
    }

    private fun setfragment(frag: RegisterFragment1) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authContainer,frag)
            .addToBackStack("register")
            .commit();
    }
}
