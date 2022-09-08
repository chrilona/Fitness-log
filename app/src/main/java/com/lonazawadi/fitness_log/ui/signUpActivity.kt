package com.lonazawadi.fitness_log.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.lonazawadi.fitness_log.R
import com.lonazawadi.fitness_log.api.ApiClient
import com.lonazawadi.fitness_log.api.ApiInterface
import com.lonazawadi.fitness_log.databinding.ActivitySignUpBinding
import com.lonazawadi.fitness_log.models.RegisterRequest
import com.lonazawadi.fitness_log.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class signUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
    }
    fun castView(){
        binding.btnSignUp.setOnClickListener { validateSignup() }
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
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
        var firstNameS=binding.etFirstname.text.toString()
        if (firstNameS.isBlank()){
            binding.tilFirstname.error="Password is required"
            error =true
        }
        var secondNameS=binding.etSecondName.text.toString()
        if (secondNameS.isBlank()){
            binding.tilSecondName.error="Password is required"
            error =true
        }
        var emailS=binding.etEmailSign.text.toString()
        if (emailS.isBlank()){
            binding.tilEmailSignUp.error="Email is required"
            error=true
        }
        var phonenumb=binding.etPhoneNumber.text.toString()
        if (phonenumb.isBlank()){
            binding.tilPhoneNumber.error="Phone number required"
            error=true
        }
        //validating input in the email field matches the pattern Regular expressions
        if (Patterns.EMAIL_ADDRESS.matcher(emailS).matches()){
            binding.tilEmailSignUp.error = "Invalid"
            error=true
        }
        var passwordS=binding.etSignPassword.text.toString()
        if (passwordS.isBlank()){
            binding.tilPasswordSignup.error="Password is required"
            error =true
        }
        var confirmS=binding.etConfirm.text.toString()
        if (confirmS.isBlank()){
            binding.tilConfirmSignup.error="Password is required"
            error =true
        }
        //comparing if passwords passed in both edit texts match.
        if (passwordS!=confirmS){
            binding.tilConfirmSignup.error="Passwords do not match"

        }
        if (!error){
            val registerRequest= RegisterRequest(firstNameS,secondNameS,emailS,passwordS,phonenumb)
            makeRegisterRequest(registerRequest)
            startActivity(Intent(this,loginActivity::class.java))
        }
    }

    fun makeRegisterRequest(registerRequest: RegisterRequest){
        val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        val request= apiClient.registerUser(registerRequest)
        request.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(baseContext,response.body()?.message,Toast.LENGTH_LONG).show()
                }
                else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

        })
}}