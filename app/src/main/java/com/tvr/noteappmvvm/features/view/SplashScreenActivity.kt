package com.tvr.noteappmvvm.features.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tvr.noteappmvvm.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val shared:SharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE)

        Thread{
            Thread.sleep(2000)
            val set: Set<String>? = shared.getStringSet("loginData", null) as? Set<String>
            if (set!=null){
                startActivity(Intent(this,HomeActivity::class.java))
            }else startActivity(Intent(this,AuthActivity::class.java));
            finish();
        }.start()
    }

}
