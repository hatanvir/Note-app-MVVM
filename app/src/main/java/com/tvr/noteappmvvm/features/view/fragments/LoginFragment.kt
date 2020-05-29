package com.tvr.noteappmvvm.features.view.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textfield.TextInputEditText
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty

import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.features.model.AuthModel
import com.tvr.noteappmvvm.features.model.AuthModelImplementation
import com.tvr.noteappmvvm.features.model.Request.LoginRequest
import com.tvr.noteappmvvm.features.view.HomeActivity
import com.tvr.noteappmvvm.features.viewmodel.AuthViewModel

class LoginFragment() : Fragment(),Validator.ValidationListener{
    @NotEmpty
    @BindView(R.id.emailLoginEt)
    lateinit var emailLoginEt:TextInputEditText
    @NotEmpty
    @BindView(R.id.passwordLoginEt)
    lateinit var passwordLoginEt:TextInputEditText

    @BindView(R.id.loginBt)
    lateinit var loginBt: Button

    private lateinit var model:AuthModel
    private lateinit var viewModel:AuthViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_login, container, false)
        ButterKnife.bind(this,v)
        var validator = Validator(this)
        validator.setValidationListener(this)

        val progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Please wait")

        // initialize model. (I know we should not initialize model in View. But for simplicity...)
        model = AuthModelImplementation(requireActivity())
        // initialize ViewModel
        viewModel = ViewModelProviders.of(requireActivity()).get(AuthViewModel::class.java)

        viewModel.loginProgressbarLiveData.observe(this, Observer { isLoading->
            if (isLoading) progressDialog.show()
            else progressDialog.dismiss()
        })
        loginBt.setOnClickListener {
            validator.validate()
        }

        return v;
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        for (error in errors!!) {
            val view = error.view
            val message = error.getCollatedErrorMessage(activity)

            // Display error messages
            if (view is EditText) {
                (view).error = message
            } else {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onValidationSucceeded() {
       getLoginstatus();
    }

    private fun getLoginstatus() {
        val sharedPreferences:SharedPreferences = activity!!.getSharedPreferences("Login", Context.MODE_PRIVATE)

        viewModel.loginInfo(LoginRequest(
            emailLoginEt.text.toString(),
            passwordLoginEt.text.toString()
        ),model)
        viewModel.loginSuccessLiveData.observe(this, Observer { data->
            Toast.makeText(activity,data.token.email,Toast.LENGTH_SHORT).show()
            val hashSet:HashSet<String> = HashSet()
            hashSet.add(data.token.name)
            hashSet.add(data.token.email)
            hashSet.add(data.token.createdAt)

            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putStringSet("loginData",hashSet).commit()

            startActivity(Intent(activity,
                HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            activity!!.finish()

        })
        viewModel.loginFailedLiveData.observe(this, Observer { error->
            Toast.makeText(activity,"Failed to sign in.Try again !"+error,Toast.LENGTH_SHORT).show()
        })
    }
}
