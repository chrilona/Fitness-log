package com.lonazawadi.fitness_log.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lonazawadi.fitness_log.R
import com.lonazawadi.fitness_log.api.ApiClient
import com.lonazawadi.fitness_log.api.ApiInterface
import com.lonazawadi.fitness_log.databinding.ActivitySignUpBinding
import com.lonazawadi.fitness_log.models.RegisterRequest
import com.lonazawadi.fitness_log.models.RegisterResponse
import com.lonazawadi.fitness_log.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class signUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData.observe(this, Observer { registerResponse->
            Toast.makeText(baseContext,registerResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,loginActivity::class.java))
        })
        userViewModel.registerErrorLiveData.observe(this, Observer { registerError->
            Toast.makeText(baseContext,registerError,Toast.LENGTH_LONG).show()
        })
    }
    fun castView(){
        binding.btnSignUp.setOnClickListener {
            validateSignup()}
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
        }
    }
    fun validateSignup(){
        var error=false
        binding.tilFirstname.error=null
        binding.tilSecondName.error=null
        binding.tilEmailSignUp.error=null
        binding.tilPasswordSignup.error=null
        binding.tilConfirmSignup.error=null
        binding.tilPhoneNumber.error=null
        val firstNameS=binding.etFirstname.text.toString()
        if (firstNameS.isBlank()){
            binding.tilFirstname.error="Password is required"
            error =true
        }
        val secondNameS=binding.etSecondName.text.toString()
        if (secondNameS.isBlank()){
            binding.tilSecondName.error="Password is required"
            error =true
        }
        val emailS=binding.etEmailSign.text.toString()
        if (emailS.isBlank()){
            binding.tilEmailSignUp.error="Email is required"
            error=true
        }
        val phonenumb=binding.etPhoneNumber.text.toString()
        if (phonenumb.isBlank()){
            binding.tilPhoneNumber.error="Phone number required"
            error=true
        }
        //validating input in the email field matches the pattern Regular expressions

        val passwordS=binding.etSignPassword.text.toString()
        if (passwordS.isBlank()){
            binding.tilPasswordSignup.error="Password is required"
            error =true
        }
        val confirmS=binding.etConfirm.text.toString()
        if (confirmS.isBlank()){
            binding.tilConfirmSignup.error="Password is required"
            error =true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailS).matches()){
            binding.tilEmailSignUp.error="Not a valid email address"
            error=true
        }
        //comparing if passwords passed in both edit texts match.
        if (passwordS!=confirmS){
            binding.tilConfirmSignup.error="Passwords do not match"

        }
        if (!error){
            val registerRequest= RegisterRequest(firstNameS,secondNameS,phonenumb,emailS,passwordS,)
            userViewModel.registeruser(registerRequest)
        }
    }
}