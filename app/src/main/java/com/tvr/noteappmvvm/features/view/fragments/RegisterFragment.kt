package com.tvr.noteappmvvm.features.view.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textfield.TextInputEditText
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password
import com.tvr.noteappmvvm.R
import com.tvr.noteappmvvm.features.model.AuthModel
import com.tvr.noteappmvvm.features.model.AuthModelImplementation
import com.tvr.noteappmvvm.features.model.Request.RegisterRequest
import com.tvr.noteappmvvm.features.viewmodel.AuthViewModel


class RegisterFragment : Fragment(),Validator.ValidationListener {

    @NotEmpty
    @BindView(R.id.nameInputEt)
    lateinit var nameInputEt:TextInputEditText
    @Email
    @BindView(R.id.emailInputEt)
    lateinit var emailInputEt:TextInputEditText

    @Password(min = 6)
    @BindView(R.id.passwordInputEt)
    lateinit var passwordInputEt:TextInputEditText
    @ConfirmPassword
    @BindView(R.id.passwordRetypeInputEt)
    lateinit var passwordRetypeInputEt:TextInputEditText

    @BindView(R.id.signUpBt)
    lateinit var signUpBt: Button

    @BindView(R.id.loginSugBt)
    lateinit var loginSugBt:Button;


    private lateinit var model: AuthModel
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_register, container, false)
        ButterKnife.bind(this,v);

        var validator = Validator(this)
        validator.setValidationListener(this)

        val progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Please wait")

        // initialize model. (I know we should not initialize model in View. But for simplicity...)
        model = AuthModelImplementation(requireActivity())
        // initialize ViewModel
        viewModel = ViewModelProviders.of(requireActivity()).get(AuthViewModel::class.java)

        viewModel.progressbarLiveData.observe(this, Observer { isLoading->
            if (isLoading) progressDialog.show()
            else progressDialog.dismiss()
        })

        signUpButtonClick(validator);

        loginSugBt.setOnClickListener {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.authContainer,
                    LoginFragment()
                )
                .addToBackStack("Login")
                .commit()
        }
        return v;
    }

    private fun getRegisterStatus() {
        viewModel.registerInfo(RegisterRequest(
            nameInputEt.text.toString(),
            emailInputEt.text.toString(),
            passwordInputEt.text.toString(),
            passwordRetypeInputEt.text.toString()),model)

        viewModel.registerSuccessLiveData.observe(this, Observer { data ->
            Toast.makeText(activity,
                "Successfully created your account", Toast.LENGTH_LONG).show()
            startActivity(Intent(activity,
                LoginFragment::class.java))
        })
        viewModel.registerFailedLiveData.observe(this, Observer { errorMessage ->
            Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
        })
    }

    private fun signUpButtonClick(validator: Validator) {
        signUpBt.setOnClickListener {
            validator.validate()
        }
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
        getRegisterStatus();
    }

}
