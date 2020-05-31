package com.tvr.noteappmvvm.features.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tvr.noteappmvvm.R
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v  =  inflater.inflate(R.layout.fragment_profile, container, false)
        setProfileData(v)
        return v;
    }

    private fun setProfileData(v:View) {
        val shared: SharedPreferences = context!!.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val set: Set<String>? = shared.getStringSet("loginData", null)
        v.profileName.text = set!!.elementAt(1)
        v.profileEmail.text = set!!.elementAt(2)
    }
}
